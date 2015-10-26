import java.io.*;
import java.net.*;

/**
 * Created by pedrocb on 20-10-2015.
 */
public class Server{

    private int udpPort;
    private String secondServerIP;
    private int secondServerPort;
    private int serverPort;

    private DatagramSocket udpSocket;

    private Listener listener;

    public Server(int port, int udpPort,String secondServerIP,int secondServerUdpPort){
        this.secondServerIP = secondServerIP;
        this.serverPort = port;
        this.secondServerPort = secondServerUdpPort;
        this.udpPort = udpPort;
        try {
            this.udpSocket = new DatagramSocket(udpPort);
            this.udpSocket.setSoTimeout(1000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        listener = new Listener(serverPort);
        tradeUdp();
    }

    public void tradeUdp(){
        int pingsfailed = 0;
        char message = 'o';
        byte[] out  = {(byte)message};
        byte[] in = new byte[1];


        while(pingsfailed<3 || new String(in).equals("o")){
            try {
                udpSocket.send(new DatagramPacket(out,1,InetAddress.getByName(secondServerIP),secondServerPort));
            } catch (IOException e) {
                System.out.println("Exception sending ping");
            }
            try {
                udpSocket.receive(new DatagramPacket(in,1,InetAddress.getByName(secondServerIP),secondServerPort));
            } catch (IOException e) {
                pingsfailed++;
            }
        }
        listener.start();
        pingsfailed = 0;
        while(true) {
            message = 'i';
            out[0] = (byte)message;
            try {
                udpSocket.send(new DatagramPacket(out,1,InetAddress.getByName(secondServerIP),secondServerPort));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                udpSocket.receive(new DatagramPacket(in,1,InetAddress.getByName(secondServerIP),secondServerPort));
            } catch (IOException e) {
                pingsfailed++;
                if(pingsfailed == 3){
                    pingsfailed = 0;
                    if(!listener.isAlive()){
                        listener.notify();
                    }
                }
            }
            if(new String(in).equals('i')){
                synchronized (listener){
                    try {
                        listener.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static void main(String args[]) throws InterruptedException {
        int udpPort = 8011;
        int serverPort = 8001;
        String secondServerIP = "localhost";
        int secondServerPort = 8012;

        new Server(serverPort,udpPort,secondServerIP,secondServerPort);
        //Thread.sleep(1000);
        //new Server(8002,8012,"localhost",8011);
    }

}

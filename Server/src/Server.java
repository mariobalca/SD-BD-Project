import java.io.*;
import java.net.*;

/**
 * Created by pedrocb on 20-10-2015.
 */
public class Server{

    enum SERVER_STATE{
        NOT_LISTENING,
        LISTENING,
        RECEIVING_REQUESTS
    }

    static SERVER_STATE state;

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


        while(pingsfailed<3 && new String(in).equals("i")){
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
        synchronized (state){
            state = SERVER_STATE.LISTENING;
        }
        pingsfailed = 0;
        while(true) {
            synchronized (state){
                message = (state == SERVER_STATE.RECEIVING_REQUESTS)?'r':'i';
            }
            System.out.println(message);
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
            if(new String(in).equals('r')){
                synchronized (listener){
                    try {
                    System.out.println("Shutting down");
                        listener.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static void main(String args[]) throws InterruptedException {
        int udpPort = 8012;
        int serverPort = 8002;
        String secondServerIP = "10.42.0.21";
        int secondServerPort = 8011;

        new Server(serverPort,udpPort,secondServerIP,secondServerPort);
        //Thread.sleep(1000);
        //new Server(8002,8012,"localhost",8011);
    }

}

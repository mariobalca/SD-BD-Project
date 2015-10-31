import com.sun.org.apache.xpath.internal.SourceTree;

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
    static String RMI_ADDRESS = "localhost";


    private DatagramSocket udpSocket;

    private Listener listener;

    public Server(int port, int udpPort,String secondServerIP,int secondServerUdpPort){
        state = SERVER_STATE.NOT_LISTENING;
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


        while(pingsfailed<3 && !new String(in).equals("o")){
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
            out[0] = (byte)message;
            try {
                udpSocket.send(new DatagramPacket(out,1,InetAddress.getByName(secondServerIP),secondServerPort));
            } catch (IOException e) {
                System.out.println("Rede não alcançavel");
            }
            try {
                udpSocket.receive(new DatagramPacket(in,1,InetAddress.getByName(secondServerIP),secondServerPort));
                if(new String(in).equals("r")){
                    synchronized (listener){
                        try {
                            System.out.println("Shutting down");
                            synchronized(state) {
                                state = SERVER_STATE.NOT_LISTENING;
                            }
                            listener.wait();
                        } catch (InterruptedException e) {
                            System.out.println("APARECEU ESTE ERRO AVISAR");
                        }
                    }
                }

            } catch (IOException e) {
                pingsfailed++;
                if(pingsfailed == 3){
                    pingsfailed = 0;
                    if(!listener.isAlive()){
                        synchronized (state){
                            state = SERVER_STATE.LISTENING;
                        }
                        listener.notify();
                    }
                }
            }

        }
    }

    public static void main(String args[]) throws InterruptedException {
        int udpPort = 8011;
        int serverPort = 8001;
        String secondServerIP = "10.42.0.1";
        int secondServerPort = 8012;

        new Server(serverPort,udpPort,secondServerIP,secondServerPort);
        //Thread.sleep(1000);
        //new Server(8001,8011,"localhost",8012);
    }

}

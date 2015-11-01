import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

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
    static String RMI_ADDRESS;


    private DatagramSocket udpSocket;

    private Listener listener;

    public Server(){

        try {
            BufferedReader fR = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream ("configServer.txt")));

            serverPort = Integer.parseInt(fR.readLine());
            udpPort=Integer.parseInt(fR.readLine());
            secondServerIP = fR.readLine();
            secondServerPort = Integer.parseInt(fR.readLine());
            RMI_ADDRESS = fR.readLine();
            fR.close();
        }
        catch (Exception e){
            System.out.println("Erro ao abrir ficheiro configServer.txt");
            System.exit(1);
        }

        state = SERVER_STATE.NOT_LISTENING;

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



    new Server();

        //Thread.sleep(1000);
        //new Server(8001,8011,"localhost",8012);
    }


    public void loadFile(){
    }
}

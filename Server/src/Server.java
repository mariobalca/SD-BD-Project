import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by pedrocb on 20-10-2015.
 */
public class Server{
    static int serverPort = 8001;

    static int udpPort = 8012;
    static String secondServerIP = "localhost";
    static int secondServerPort = 8011;

    public Server(int port, int udpPort,String secondServerIP,int secondServerUdpPort){
        Listener listener = new Listener(port);
        new TradeUDP(secondServerIP,secondServerUdpPort,udpPort,listener);

    }

    public static void main(String args[]){
        new Server(serverPort,udpPort,secondServerIP,secondServerPort);
        new Server(8002,8012,"localhost",8011);
    }

}

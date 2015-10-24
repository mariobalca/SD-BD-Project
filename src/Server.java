import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by pedrocb on 20-10-2015.
 */
public class Server{
    static int serverPort = 8002;

    static int udpPort = 8011;
    static String secondServerIP = "localhost";
    static int secondServerPort = 8012;

    public static void main(String args[]){
        Listener listener = new Listener(serverPort);
        new TradeUDP(secondServerIP,secondServerPort,udpPort,listener);
        synchronized (listener) {
            try {
                listener.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

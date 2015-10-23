import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by pedrocb on 20-10-2015.
 */
public class Server {
    static int serverPort = 8001;

    public static void main(String args[]){
        try{
            System.out.println("Listening on " + serverPort);
            ServerSocket listensocket = new ServerSocket(serverPort);
            while (true){
                Socket clientSocket = listensocket.accept();
                new Connection(clientSocket);
            }


        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

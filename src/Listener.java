import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by pedro on 24-10-2015.
 */
public class Listener extends Thread {

    private int serverPort;


    public Listener(int serverPort){
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        try {
            System.out.println("Listening on " + serverPort);
            ServerSocket listensocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = listensocket.accept();
                new Connection(clientSocket);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

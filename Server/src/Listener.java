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
                synchronized (Server.state){
                    Server.state = Server.SERVER_STATE.RECEIVING_REQUESTS;

                }
                new Connection(clientSocket);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getServerPort(){
        return serverPort;
    }

}

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by pedro on 24-10-2015.
 */
public class Listener extends Thread {

    private int serverPort;
    private Server.SERVER_STATE state;

    public Listener(int serverPort, Server.SERVER_STATE state){
        this.serverPort = serverPort;
        this.state = state;
    }

    @Override
    public void run() {
        try {
            System.out.println("Listening on " + serverPort);
            ServerSocket listensocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = listensocket.accept();
                synchronized (state){
                    state = Server.SERVER_STATE.RECEIVING_REQUESTS;
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

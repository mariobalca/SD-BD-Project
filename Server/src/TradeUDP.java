import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.IOException;
import java.net.*;

/**
 * Created by pedro on 24-10-2015.
 */
public class TradeUDP extends Thread{
    private String serverHost;
    private int serverPort;
    private int port;
    private DatagramSocket socket;
    private Listener listener;

    public TradeUDP(String serverHost, int serverPort,int port,Listener thread){
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        this.port = port;
        listener = thread;
        try {
            socket = new DatagramSocket(port);
            socket.setSoTimeout(2000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        start();
    }

    public void run(){
        int pingsfailed = 0;
        while(true){
            byte[] in = new byte[1];
            char message = (!listener.isAlive())?'o':'i';
            byte[] out = {(byte)message};
            try {
                socket.send(new DatagramPacket(out, 1, InetAddress.getByName(serverHost), serverPort));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.receive(new DatagramPacket(in,1));
                String ping = new String(in);
                pingsfailed=0;
                if(ping.equals("i")){
                    synchronized (listener) {
                        if (listener.isAlive()) {
                            try {
                                System.out.println("Not listening on "+listener.getServerPort() +"!");
                                listener.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                else{
                    if(listener.getState() == State.WAITING){
                        synchronized (listener) {
                            listener.notify();
                        }
                    }
                    else if(listener.getState() == State.NEW){
                        listener.start();
                    }
                }
            } catch (IOException e) {
                pingsfailed++;
                if (pingsfailed == 3) {
                    System.out.println("Não recebi 3 pings");
                    pingsfailed = 0;
                    if(listener.getState() == State.WAITING){
                        synchronized (listener) {
                            listener.notify();
                        }
                    }
                    else if(listener.getState() == State.NEW){
                        listener.start();
                    }
                }
            }

        }
    }
}

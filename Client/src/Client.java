import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private String[] hosts;
    private int[] ports;

    private String currentString;

    public Client(String[] hosts, int[] ports){
        this.hosts = hosts;
        this.ports = ports;
        this.currentString = new String("vazio");

        new IOThread(currentString);

        int currentServer = 0;
        Socket socket = null;
        while(true){
            try{
                socket = new Socket();
                socket.connect(new InetSocketAddress(hosts[currentServer],ports[currentServer]), 3000);
                socket.setKeepAlive(true);
                socket.setSoTimeout(5000);
                System.out.println("Established connection with " + hosts[currentServer] + '/' + ports[currentServer]);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream());
                String input;
                while (true){
                    synchronized (currentString) {
                        if(currentString.equals("vazio"))
                            input = "ping";
                        else{
                            input = currentString;
                        }
                        out.writeUTF(input);

                    }
                    String data;
                    try{
                        data = in.readUTF();
                    }
                    catch (IOException e){
                        System.out.println("Nao recebeu resposta o servidor");
                        break;
                    }

                }
            }catch (IOException e){
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                currentServer = ((currentServer==1)?0:1);
            }
        }
    }

    public static void main(String[] args) {
        String[] hosts= {
                "localhost",
                "localhost"
        };

        int[] ports={
                8001, 8002
        };
        new Client(hosts,ports);
    }
}



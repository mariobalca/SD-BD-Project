import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    static String[] hosts= {
        "localhost",
        "localhost"
    };

    static int[] ports={
            8001, 8002
    };

    public static void main(String[] args) {
        int currentServer = 0;
        while(true){
            try{
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(hosts[currentServer],ports[currentServer]), 3000);
                socket.setKeepAlive(true);
                socket.setSoTimeout(5000);
                System.out.println("Established connection with " + hosts[currentServer] + '/' + ports[currentServer]);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream());
                String input = "";
                while (true){
                    try {
                        input = reader.readLine();
                    }catch (Exception e) {
                    }
                    out.writeUTF(input);
                    String data;
                    try{
                        data = in.readUTF();
                    }
                    catch (IOException e){
                        System.out.println("Nao recebeu resposta o servidor");
                        break;
                    }
                    System.out.println(data);
                }
            }catch (IOException e){
                System.out.println(currentServer);
                currentServer = ((currentServer==1)?0:1);
            }
        }

    }
}



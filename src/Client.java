import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.net.Socket;
import java.time.Clock;

public class Client {

    static String[] hosts= {
        "localhost",
        "localhost"
    };

    static int[] ports={
            8001, 8002
    };

    public static void main(String[] args) {
        long lasttime = System.currentTimeMillis();
        int currentServer = 0;
        while(true){
            try{
                Socket socket = new Socket(hosts[currentServer],ports[currentServer]);
                lasttime = System.currentTimeMillis();
                System.out.println("Established connection with " + hosts[currentServer] + '/' + ports[currentServer]);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream());
                String input = "";
                while (true){
                    System.out.println("Test");
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
                currentServer = ((currentServer==1)?0:1);
            }
        }

    }
}



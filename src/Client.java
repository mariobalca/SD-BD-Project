import java.io.*;
import java.net.Socket;

public class Client {
    static String primaryServerIP = "localhost";
    static int primaryServerPort = 8001;

    static String secondaryServerIP = "localhost";
    static int secondaryServerPort = 8002;

    public static void main(String[] args) {
        try{
            Socket socket = new Socket(primaryServerIP, primaryServerPort);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String input = "";
            while (true){

                try {
                    input = reader.readLine();
                }catch (Exception e){

                }
                out.writeUTF(input);
                String data = in.readUTF();
                System.out.println(data);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

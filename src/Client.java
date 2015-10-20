import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost" , 8001);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String input ="";
            while (true){

                try {
                    input = reader.readLine();
                }catch (Exception e){

                }
                out.writeUTF(input);
                String data = in.readUTF();
                System.out.printf(data);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

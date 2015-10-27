import java.io.*;
import java.net.Socket;

class Connection extends Thread{
    DataInputStream inputStream;
    DataOutputStream outputStream;
    Socket socket;
    public Connection(Socket socket){
        this.socket = socket;
        try {
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    public void run(){
        try{
            while(true){
                //an echo server
                String data = inputStream.readUTF();
                System.out.println(data);
                String resposta;
                if(data.equals("ping")){
                    resposta = "ping";
                }
                else{
                    System.out.println("Recebeu " + data);
                    resposta= data.replaceAll("a","o");
                    System.out.println(resposta);
                }
                outputStream.writeUTF(resposta);
            }
        }catch(EOFException e){System.out.println("EOF:" + e);
        }catch(IOException e){System.out.println("IO:" + e);}
    }
}
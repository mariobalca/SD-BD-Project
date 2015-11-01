import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;

class Connection extends Thread{
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    Socket socket;
    RMI rmi;
    public Connection(Socket socket){
        this.socket = socket;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            rmi = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS, 7000).lookup("rmi");
            System.out.println("Connected to RMI and Client");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        start();
    }

    public void run(){
        try{
            while(true){
                //an echo server
                Request data = (Request)inputStream.readObject();
                Response resposta;
                if(!data.getTipo().equals("Ping")) {
                    System.out.println(data.getTipo());
                    resposta = data.execute(rmi);
                }
                else{
                    resposta = new Response("Ping");
                }
                outputStream.writeObject(resposta);
            }
        }catch(EOFException e){System.out.println("EOF:" + e);
        }catch(IOException e){System.out.println("IO:" + e);} catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
import sun.rmi.runtime.Log;

import java.io.*;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
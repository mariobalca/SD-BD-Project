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
    public Executioner executioner;

    public Response response = new Response("Ping");
    public Request request = new Ping();
    public boolean resposeToSend = false;
    public boolean requestProcessing = false;

    public synchronized Request getRequest() {
        return request;
    }

    public synchronized void setResponse(Response response) {
        this.response = response;
        this.resposeToSend = true;
        this.requestProcessing = false;
    }


    public Connection(Socket socket){
        this.socket = socket;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.executioner = new Executioner(this);
            System.out.println("Connected to RMI and Client");
        } catch (IOException e) {
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
                    if(!requestProcessing && !resposeToSend) {
                        synchronized (request) {
                            request = data;
                        }
                        requestProcessing = true;
                        synchronized (executioner) {
                            if (executioner.getState() == State.NEW) {
                                executioner.start();
                            } else if (!(executioner.getState() == State.RUNNABLE)) {
                                executioner.notify();
                            }
                        }
                    }
                }
                if(resposeToSend){
                    synchronized (response){
                        resposta = response;
                    }
                    resposeToSend = false;
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
package server;

import genericclasses.Ping;
import genericclasses.Request;
import genericclasses.Response;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
            System.out.println("Connected to rmi.RMI and client.Client");
        } catch (IOException e) {
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
        }
    }

}
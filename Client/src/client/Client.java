package client;

import genericclasses.Ping;
import genericclasses.Request;
import genericclasses.RequestResponse;
import genericclasses.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private String[] hosts ;
    protected int[] ports;

    private IOThread ioThread;
    public static int userId;
    public static int requestId;
    private int timeout;

    static RequestResponse currentRequest = new RequestResponse();
    static Boolean requestToSend = false;

    public Client(){
        hosts = new String[2];
        ports = new int[2];
        this.userId = 0;
        loadFile();

        ioThread = new IOThread();


        //Carrega as configs do cliente


        int currentServer = 0;
        Socket socket = null;
        while(true){
            try{
                socket = new Socket();
                socket.connect(new InetSocketAddress(hosts[currentServer],ports[currentServer]), 3000);
                socket.setSoTimeout(timeout);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request input;
                synchronized (ioThread){
                    if(ioThread.getState() == IOThread.State.NEW)
                        ioThread.start();
                }
                while (true){
                    synchronized (currentRequest) {
                        synchronized (requestToSend) {
                            if(requestToSend) {
                                input = currentRequest.request;
                            }
                            else
                                input = new Ping();
                        }
                    }
                    out.writeObject(input);
                    Response data;
                    try{
                        data = (Response)in.readObject();
                        if(!data.tipo.equals("Ping")){
                            synchronized (requestToSend){
                                requestToSend = false;
                            }
                            currentRequest.response = data;
                            synchronized (ioThread) {
                                ioThread.notify();
                            }
                        }
                    }
                    catch (IOException ex){
                        break;
                    } catch (ClassNotFoundException e) {
                    }

                }
            }catch (IOException e){
                try {
                    socket.close();
                } catch (IOException e1) {
                }
                currentServer = ((currentServer==1)?0:1);
            }
        }
    }

    public static void main(String[] args) {
        new Client();
    }

    public void loadFile(){
        try {
            BufferedReader fR = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream ("configClient.txt")));

            hosts[0]=fR.readLine();
            hosts[1]=fR.readLine();

            ports[0]=Integer.parseInt(fR.readLine());
            ports[1]=Integer.parseInt(fR.readLine());
            timeout = Integer.parseInt(fR.readLine());

            fR.close();
        }
        catch (Exception e){
            System.out.println("Erro ao abrir ficheiro configClient");
            System.exit(1);
        }
    }
}





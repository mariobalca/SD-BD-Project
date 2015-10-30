import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private String[] hosts;
    private int[] ports;

    private IOThread ioThread;
    static int userId;
    static int requestId;


    static RequestResponse currentRequest = new RequestResponse();
    static Boolean requestToSend = false;

    public Client(String[] hosts, int[] ports){
        this.hosts = hosts;
        this.ports = ports;
        this.userId = 0;
        ioThread = new IOThread();



        int currentServer = 0;
        Socket socket = null;
        while(true){
            try{
                socket = new Socket();
                socket.connect(new InetSocketAddress(hosts[currentServer],ports[currentServer]), 3000);
                socket.setKeepAlive(true);
                socket.setSoTimeout(5000);
                System.out.println("Established connection with " + hosts[currentServer] + '/' + ports[currentServer]);
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
                                System.out.println(input.tipo);
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
                    catch (IOException e){
                        System.out.println("Nao recebeu resposta o servidor");
                        break;
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }catch (IOException e){
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                currentServer = ((currentServer==1)?0:1);
            }
        }
    }

    /*private void execute(RequestResponse data) {
        switch (data.response.tipo){
            case "Login":
                IntResponse intresponse = (IntResponse)data.response;
                userId = intresponse.values[0];
                requestId = intresponse.values[1];
                if(userId>0){
                    System.out.println("Login efectuado");

                }
                else{
                    System.out.println("Invalid credentials");
                }
                break;
            case "Register":
                IntResponse response4 = (IntResponse)data.response;
                userId = response4.values[0];
                requestId = 0;
                if(userId>0){
                    System.out.println("Registo efectuado");

                }
                else{
                    System.out.println("Invalid credentials");
                }
                break;
            case "ListActualProj":
                ProjectListResponse projectresponse = (ProjectListResponse)data.response;
                for(int i=0;i<projectresponse.projects.size();i++){
                    System.out.println(projectresponse.projects.get(i));
                }
                break;
            case "ConsultProj":
                projectresponse = (ProjectListResponse)data.response;
                System.out.println(projectresponse.projects.get(0) + " Buéda detalhado");
                break;
            case "ListOlderProj":
                projectresponse = (ProjectListResponse)data.response;
                for(int i=0;i<projectresponse.projects.size();i++){
                    System.out.println(projectresponse.projects.get(i));
                }
                break;


        }
        synchronized (ioThread) {
            ioThread.notify();
        }
    }
*/
    public static void main(String[] args) {
        String[] hosts= {
                "localhost",
                "localhost"
        };

        int[] ports={
                8001, 8002
        };
        new Client(hosts,ports);
    }
}



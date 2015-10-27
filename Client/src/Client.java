import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import jdk.nashorn.internal.ir.RuntimeNode;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private String[] hosts;
    private int[] ports;

    private IOThread ioThread;
    private boolean auth;

    static Request currentRequest;
    static String aux;



    public Client(String[] hosts, int[] ports){
        this.hosts = hosts;
        this.ports = ports;
        this.currentRequest = new Ping();
        this.aux = "O que deseja?\n1.Login";
        this.auth = false;
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
                RequestResponse input;
                synchronized (ioThread){
                    ioThread.start();
                }
                while (true){
                    synchronized (currentRequest) {
                        input = new RequestResponse(currentRequest);
                        out.writeObject(input);
                    }
                    RequestResponse data;
                    try{
                        data = (RequestResponse)in.readObject();
                        if(!data.response.tipo.equals("Ping")){
                            System.out.println(data.response.tipo);
                            execute(data);
                            synchronized (currentRequest){
                                currentRequest = new Ping();
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

    private void execute(RequestResponse data) {
        switch (data.response.tipo){
            case "Login":
                BooleanResponse response = (BooleanResponse)data.response;
                auth = response.status;
                if(auth){
                    System.out.println("Login efectuado");
                    synchronized (aux){
                        aux = "1.Check Balance";
                    }
                }
                else{
                    System.out.println("Invalid credentials");
                    synchronized (aux){
                        aux = "1.List Projects";
                    }
                }

        }
        synchronized (ioThread) {
            ioThread.notify();
        }
    }

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



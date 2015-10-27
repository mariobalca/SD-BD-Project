import sun.rmi.runtime.Log;

import java.io.*;
import java.net.Socket;

class Connection extends Thread{
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    Socket socket;
    public Connection(Socket socket){
        this.socket = socket;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    public void run(){
        try{
            while(true){
                //an echo server
                RequestResponse data = (RequestResponse)inputStream.readObject();
                Response resposta;
                if(!data.request.getTipo().equals("Ping")) {
                    System.out.println(data.request.getTipo());
                    resposta = execute(data.request);
                }
                else{
                    resposta = new Response("Ping");
                }
                data.response = resposta;
                outputStream.writeObject(data);
            }
        }catch(EOFException e){System.out.println("EOF:" + e);
        }catch(IOException e){System.out.println("IO:" + e);} catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Response execute(Request request){
        switch (request.tipo){
            case "Login":
                Login aux = (Login)request;
                boolean auth = false;
                if(aux.username.equals("Tobias") && aux.password.equals("LOL")){
                    auth = true;
                }
                return new BooleanResponse("Login",auth);
        }
        return null;
    }
}
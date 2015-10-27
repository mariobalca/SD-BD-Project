import sun.rmi.runtime.Log;

import java.io.*;
import java.net.Socket;
import java.util.Date;

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
            case "ListActualProj":
                ProjectListResponse response = new ProjectListResponse("ListActualProj");
                response.projects.add(new Project(0,"Ganza", new Date(2015,4,20,4,20),420000,"Purex"));
                response.projects.add(new Project(1,"Bombardeamento do NEI",new Date(2001,9,11,15,10),666000,"Tudo morto"));
                return response;
            case "ConsultProj":
                ProjectListResponse response1 = new ProjectListResponse("ConsultProj");
                response1.projects.add(new Project(0,"Ganza", new Date(2015,4,20,4,20),420000,"Purex"));
                return response1;

        }
        return null;
    }
}
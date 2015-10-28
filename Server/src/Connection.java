import sun.rmi.runtime.Log;

import java.io.*;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
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
            socket.setKeepAlive(true);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            rmi = (RMI) LocateRegistry.getRegistry("localhost",7000).lookup("rmi");
        } catch (RemoteException e){
            System.out.println("OYOYO");
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NotBoundException e) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Response execute(Request request) throws RemoteException, SQLException {
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
                ArrayList<Project> projects = rmi.getProjects();
                for(Project item : projects){
                    response.projects.add(item);
                }
                return response;
            case "ListOlderProj":
                ProjectListResponse response2 = new ProjectListResponse("ListOlderProj");
                ArrayList<Project> projects2 = rmi.getOlderProjects();
                for(Project item : projects2){
                    response2.projects.add(item);
                }
                return response2;
            case "ConsultProj":
                ProjectListResponse response1 = new ProjectListResponse("ConsultProj");
                response1.projects.add(new Project(0,"Ganza", new Date(2015,4,20,4,20),420000,"Purex"));
                return response1;

        }
        return null;
    }
}
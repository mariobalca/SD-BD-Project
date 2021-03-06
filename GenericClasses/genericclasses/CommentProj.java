package genericclasses;

import client.Client;
import rmi.RMI;
import server.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Rui on 27/10/2015.
 */
public class CommentProj extends Request{
    public int proj;
    public String subject = "", question = "";
    public int requestId;

    public CommentProj(ArrayList<Project> projects){
        super("CommentProj");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Definir o projeto a comentar
        try{
            boolean verifica = false;
            while(!verifica){
                System.out.println("Write the project Id to question: ");
                try {
                    proj = Integer.parseInt(reader.readLine());
                }catch (Exception e){
                    System.out.println("Input Error");
                    continue;
                }
                for(Project p : projects){
                    if(proj == p.getId())
                        verifica = true;
                }
                if (verifica == false)
                    System.out.println("Invalid id");
            }
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }

        //Definir o subject da mensagem
        System.out.println("Write the subject of the question: ");
        try{
            subject = (reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }


        //Definir a mensagem
        System.out.println("Write your question: ");
        try{
            question = (reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }
        requestId = ++Client.requestId;
    }
    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                rmiServer = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS, Server.rmiPort).lookup("rmi");
                return new BooleanResponse("CommentProj", rmiServer.sendMessage(new Message(subject, question, userId), proj, requestId));
            } catch (RemoteException e) {
                verifica = false;
            } catch (SQLException e) {

            } catch (NotBoundException e) {

            }
        }

        return new BooleanResponse("CommentProj", false);
    }
}

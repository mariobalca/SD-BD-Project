package repositories;

import genericclasses.Message;
import genericclasses.Reward;
import rmi.RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Rui on 18/12/2015.
 */
public class MessageRepository {
    private RMI rmiServer;

    public MessageRepository(){
        try {
            this.rmiServer = (RMI) LocateRegistry.getRegistry("localhost",7000).lookup("rmi");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
    public boolean sendMessage(Message message, int projId, int requestId){
        boolean successful;
        try {
            successful = rmiServer.sendMessage(message,projId,requestId);
        } catch (RemoteException e) {
            successful = false;
        } catch (SQLException e) {
            successful = false;
        }
        return successful;
    }

    public boolean answerMessage(int messageId, String response,int requestId,  int userId){
        boolean successful;
        try {
            successful = rmiServer.answerMessage(messageId,response,requestId,userId);
        } catch (RemoteException e) {
            successful = false;
        } catch (SQLException e) {
            successful = false;
        }
        return successful;
    }

    public ArrayList<Message> getProjectMessages(int projId){
        ArrayList<Message> messages;
        try {
            messages = rmiServer.getProjectMessages(projId);
            return messages;
        } catch (RemoteException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}

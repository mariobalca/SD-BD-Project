package models;

import genericclasses.Message;
import rmi.RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

/**
 * Created by Rui on 15/12/2015.
 */
public class MessageBean {
    public String subject, question;
    private RMI server;

    public MessageBean() {
        try {
            server = (RMI) LocateRegistry.getRegistry("localhost",7000).lookup("rmi");
        }
        catch(NotBoundException | RemoteException e) {
            e.printStackTrace(); // what happens *after* we reach this line?
        }
    }

    public boolean sendMessage(String subject , String question, int userId, int projId, int requestId){
        try {
            return server.sendMessage(new Message(subject,question,userId),projId,requestId);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean answerMessage(int messageId, String response, int requestId, int userId){
        try {
            server.answerMessage(messageId,response,requestId,userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

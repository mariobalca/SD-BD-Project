package genericclasses;

import rmi.RMI;
import server.Server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mariobalca on 01-11-2015.
 */
public class ListProjectMessages extends Request {
    public int proj;

    public ListProjectMessages(int proj){
        super("ListProjectMessages");
        this.proj = proj;
    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                rmiServer = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS, Server.rmiPort).lookup("rmi");
                ArrayList<Message> messages = rmiServer.getProjectMessages(proj);
                MessageListResponse response = new MessageListResponse("ListProjectMessages", messages);
                return response;
            } catch (RemoteException e) {
                verifica = false;
            } catch (SQLException e) {

            } catch (NotBoundException e) {

            }
        }

        return new MessageListResponse("ListProjectMessages", new ArrayList<Message>());
    }
}

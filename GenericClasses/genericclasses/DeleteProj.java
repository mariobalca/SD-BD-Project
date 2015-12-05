package genericclasses;

import client.Client;
import rmi.RMI;
import server.Server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

/**
 * Created by Rui on 27/10/2015.
 */
public class DeleteProj extends Request{
    public int proj;
    public int requestId;
    public DeleteProj(int projectId) {
        super("DeleteProj");

        this.proj = projectId;
        requestId = ++Client.requestId;
    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                rmiServer = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS, Server.rmiPort).lookup("rmi");
                return new BooleanResponse("DeleteProj",rmiServer.cancelProject(proj, requestId, userId));
            } catch (RemoteException e) {
                verifica = false;
            } catch (SQLException e) {
            } catch (NotBoundException e) {
            }
        }

        return new BooleanResponse("CommentProj", false);
    }
}

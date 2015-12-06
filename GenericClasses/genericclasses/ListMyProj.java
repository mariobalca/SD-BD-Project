package genericclasses;

import client.Client;
import rmi.RMI;
import server.Server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by pedro on 31-10-2015.
 */
public class ListMyProj extends Request {
    public int userId;

    public ListMyProj() {
        super("ListMyProj");
        userId = Client.userId;
    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                ArrayList<Project> projects = rmiServer.getAdminProjects(userId);
                ProjectListResponse response = new ProjectListResponse("ListMyProj", projects);
                return response;
            } catch (RemoteException e) {
                verifica = false;
                try {
                    rmiServer = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS, Server.rmiPort).lookup("rmi");
                } catch (RemoteException e1) {

                } catch (NotBoundException e1) {
                    e1.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new ProjectListResponse("ListMyProj", new ArrayList<Project>());
    }
}

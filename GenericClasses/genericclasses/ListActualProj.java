package genericclasses;

import rmi.RMI;
import server.Server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Rui on 26/10/2015.
 */
public class ListActualProj extends Request{
    public ListActualProj(){
        super("ListActualProj");
    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                rmiServer = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS, Server.rmiPort).lookup("rmi");
                ArrayList<Project> projects = rmiServer.getCurrentProjects();
                ProjectListResponse response = new ProjectListResponse("ListActualProj", projects);
                return response;
            } catch (RemoteException e) {
                verifica = false;
            } catch (SQLException e) {

            } catch (NotBoundException e) {

            }
        }

        return new ProjectListResponse("ListActualProj", new ArrayList<Project>());
    }
}

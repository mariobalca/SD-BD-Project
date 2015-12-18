package repositories;

import genericclasses.Path;
import rmi.RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by pedrocb on 18/12/2015.
 */
public class PathRepository {
    private RMI rmiServer;

    public PathRepository(){
        try {
            this.rmiServer = (RMI) LocateRegistry.getRegistry("localhost",7000).lookup("rmi");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Path> getProjectPaths(int projectId){
        try {
            return rmiServer.getProjectPaths(projectId);
        } catch (RemoteException e) {
            return null;
        } catch (SQLException e) {
            return null;
        }
    }
}

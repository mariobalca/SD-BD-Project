package repositories;

import genericclasses.Extra;
import rmi.RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by pedrocb on 17/12/2015.
 */
public class ExtraRepository {
    private RMI rmiServer;

    public ExtraRepository(){
        try {
            this.rmiServer = (RMI) LocateRegistry.getRegistry("localhost",7000).lookup("rmi");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public boolean createExtra(Extra extra,int requestId,int projectId,int userId){
        boolean successful;
        try {
            successful = rmiServer.createExtraLevel(extra,requestId,projectId,userId);
        } catch (RemoteException e) {
            successful = false;
        } catch (SQLException e) {
            successful = false;
        }
        return successful;
    }

    public boolean removeExtra(int extraId, int requestId, int userId){
        boolean successful;
        try {
            successful = rmiServer.removeExtraLevel(extraId,requestId,userId);
        } catch (RemoteException e) {
            successful = false;
        } catch (SQLException e) {
            successful = false;
        }
        return successful;
    }

    public ArrayList<Extra> getProjectExtras(int projectId){
        try {
            return rmiServer.getProjectExtraLevels(projectId);
        } catch (RemoteException e) {
            return null;
        } catch (SQLException e) {
            return null;
        }
    }
}

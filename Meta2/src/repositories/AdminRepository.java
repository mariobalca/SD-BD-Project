package repositories;

import rmi.RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

/**
 * Created by pedrocb on 18/12/2015.
 */
public class AdminRepository {
    private RMI rmiServer;
    public AdminRepository(){
        try {
            this.rmiServer = (RMI) LocateRegistry.getRegistry("localhost",7000).lookup("rmi");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
    public boolean addAdmin(int projectId,int requestId,int userId,String newAdminName){
        try {
            return rmiServer.addAdmin(projectId,requestId,userId,newAdminName);
        } catch (RemoteException e) {
            return false;
        } catch (SQLException e) {
            return false;
        }
    }
}

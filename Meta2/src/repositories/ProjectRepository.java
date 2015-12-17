package repositories;

import genericclasses.Project;
import rmi.RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

/**
 * Created by pedrocb on 17/12/2015.
 */
public class ProjectRepository {
    private RMI rmiServer;

    public ProjectRepository(){
        try {
            this.rmiServer = (RMI) LocateRegistry.getRegistry("localhost",7000).lookup("rmi");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public boolean createProject(Project project,int requestId,int userId){
        boolean successful;
        try {
            successful = rmiServer.createProject(project,requestId,userId);
        } catch (RemoteException e) {
            successful = false;
        } catch (SQLException e) {
            successful = false;
        }
        return successful;
    }

    public boolean deleteProject(int projectId,int requestId,int userId){
        boolean successful;
        try {
            successful = rmiServer.cancelProject(projectId,requestId,userId);
        } catch (RemoteException e) {
            successful = false;
        } catch (SQLException e) {
            successful = false;
        }
        return successful;
    }


}

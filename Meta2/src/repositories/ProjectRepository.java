package repositories;

import genericclasses.Project;
import rmi.RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public boolean financeProject(int projectId,int requestId,int userId,int pathId,double value){
        boolean successful;
        try {
            successful = rmiServer.financeProject(projectId,requestId,userId,pathId,value);
        } catch (RemoteException e) {
            successful = false;
        } catch (SQLException e) {
            successful = false;
        }
        return successful;
    }

    public ArrayList<Project> getCurrentProjects(){
        try{
            return rmiServer.getCurrentProjects();
        } catch (RemoteException e) {
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<Project> getOlderProjects(){
        try {
            return rmiServer.getOlderProjects();
        } catch (RemoteException e) {
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<Project> getAdminProjects(int userId){
        try {
            return rmiServer.getAdminProjects(userId);
        } catch (RemoteException e) {
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

}

package repositories;

import genericclasses.Project;
import rmi.RMI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by pedrocb on 17/12/2015.
 */
public class ProjectRepository {
    private RMI rmiServer;

    public ProjectRepository(){
        String rmiAddress = null;
        int rmiPort = 0;
        try {
            try {
                BufferedReader fR = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream ("../configAPI.txt")));
                rmiAddress = fR.readLine();
                rmiPort = Integer.parseInt(fR.readLine());
                fR.close();
            }
            catch (Exception e){
                System.out.println("Erro ao abrir ficheiro configAPI.txt");
                System.exit(1);
            }
            this.rmiServer = (RMI) LocateRegistry.getRegistry(rmiAddress,rmiPort).lookup("rmi");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public boolean createProject(Project project,int requestId,int userId){
        boolean successful;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            project.setDeadline(format.parse(project.getDeadlineApi()));
        } catch (ParseException e) {
        }
        try {
            successful = rmiServer.createProject(project,requestId,userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            successful = false;
        } catch (SQLException e) {
            e.printStackTrace();
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

    public Project getProject(int id) {
        try {
            return rmiServer.getProject(id);
        } catch (RemoteException e) {
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public Double getProjectValue(int projectId){
        try {
            return rmiServer.getProjectValue(projectId);
        } catch (RemoteException e) {
            return null;
        } catch (SQLException e) {
            return null;
        }
    }
}

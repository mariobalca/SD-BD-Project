package models;

import genericclasses.*;
import rmi.RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rui on 15/12/2015.
 */
public class ProjBean {
    private String name;
    private Date deadline;
    private double objective;
    private String description;
    private boolean active;
    private ArrayList<Message> messages;
    private ArrayList<Extra> extras;
    private ArrayList<Reward> rewards;
    private ArrayList<Path> paths;
    private RMI server;
    private Project project;
    private ArrayList<Project> projects;


    public ProjBean() {
        try {
            server = (RMI) LocateRegistry.getRegistry("localhost",7000).lookup("rmi");
        }
        catch(NotBoundException | RemoteException e) {
            e.printStackTrace(); // what happens *after* we reach this line?
        }
    }

    public boolean createProj(int requestId, int userId){
        Project proj = new Project(name,deadline,objective,description, active);
        proj.setRewards(rewards);
        proj.setPaths(paths);
        try {
            return server.createProject(proj,requestId,userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Project> listOlderProjects(){
        try {
            return projects = server.getOlderProjects();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Project consultProject(int id){
        try {
            project = server.getProject(id);
            return project;
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

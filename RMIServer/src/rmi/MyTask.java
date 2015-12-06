package rmi;


import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;
import genericclasses.Project;


/**
 * Created by mariobalca on 28-10-2015.
 */
class MyTask extends TimerTask {
    static RMI serverInstance;
    public MyTask(RMI rmiServer){
        serverInstance = rmiServer;
    }


    public void run() {
        try {
            ArrayList<Project> projects = serverInstance.getProjectsWithoutDetails();
            for(Project p : projects){
                if(p.getDeadline().before(new Date())){
                    serverInstance.endProject(p.getId());
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Hi see you after 30 seconds");
    }

}
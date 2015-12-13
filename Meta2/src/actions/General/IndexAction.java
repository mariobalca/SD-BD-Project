package actions.General;

import genericclasses.Project;
import org.apache.struts2.interceptor.SessionAware;
import rmi.RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by pedro on 12/8/15.
 */
public class IndexAction implements SessionAware {
    private Map<String,Object> session;
    private double balance;
    private boolean logged;
    private ArrayList<Project> projects;

    public String execute(){
        try {
            logged = session.containsKey("username");
            if(logged) {
                balance = getRMI().getBalance((Integer) session.get("usernameId"));
            }
            projects = getRMI().getCurrentProjects();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        return "success";
    }

    public boolean getLogged() {
        return logged;

    }
    public void setLogged(boolean logged){
        this.logged = logged;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public RMI getRMI() throws RemoteException, NotBoundException {
        if(!session.containsKey("rmi")){
            RMI rmi = (RMI) LocateRegistry.getRegistry("localhost",7000).lookup("rmi");
            setRMI(rmi);
        }
        return (RMI)session.get("rmi");
    }

    public void setRMI(RMI rmi){
        session.put("rmi",rmi);
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }
}

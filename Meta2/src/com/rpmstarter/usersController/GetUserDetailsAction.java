package com.rpmstarter.usersController;

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
public class GetUserDetailsAction implements SessionAware {
    private Map<String,Object> session;
    private double balance;
    private String username;

    public String getUsername() {
        return (String) session.get("username");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String execute(){
        try {
            balance = getRMI().getBalance((Integer) session.get("usernameId"));
            return "success";
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        return "success";
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
}

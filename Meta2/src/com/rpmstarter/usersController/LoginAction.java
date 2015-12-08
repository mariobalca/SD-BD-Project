package com.rpmstarter.usersController;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import rmi.RMI;
import server.Server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by pedro on 12/4/15.
 */


public class LoginAction implements SessionAware{
    private String username = null, password = null;
    private String error;
    private Map<String, Object> session;

    public String execute(){
        try {
            if(getRMI().loginUser(username,password)[0]>0){
                session.put("username",username);
                return "success";
            }
            else{
                setError("Wrong Username/Password");
                return "failed";
            }
        } catch (NotBoundException e) {
            return "rmidown";
        } catch (RemoteException e) {
            return "rmidown";
        } catch (SQLException e) {
            return "rmidown";
        }
    }

    public String logged(){
        if(session.containsKey("username")){
            return "logged";
        }
        return "notlogged";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}

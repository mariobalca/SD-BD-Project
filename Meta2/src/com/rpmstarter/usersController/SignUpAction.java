package com.rpmstarter.usersController;

import org.apache.struts2.interceptor.SessionAware;
import rmi.RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by pedro on 12/8/15.
 */
public class SignUpAction implements SessionAware{
    private Map<String,Object> session;
    private String username, password;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String execute(){
        try {
            int userId = getRMI().registerUser(username,password);
            if(userId>1) {
                session.put("username", username);
                session.put("usernameId",userId);
                session.put("requestId",0);
                return "success";
            }
            else{
                setError("That username already exists");
                return "failed";
            }
        } catch (NotBoundException e) {
            e.printStackTrace();
            return "rmidown";
        } catch (RemoteException e) {
            e.printStackTrace();
            return "rmidown";
        } catch (SQLException e) {
            e.printStackTrace();
            return "rmidown";
        }
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
package models;

import rmi.RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mariobalca on 12/13/15.
 */
public class UserBean {
    private RMI server;
    private String username; // username and password supplied by the user
    private String password;

    public UserBean() {
        try {
            server = (RMI) LocateRegistry.getRegistry("localhost",7000).lookup("rmi");
        }
        catch(NotBoundException | RemoteException e) {
            e.printStackTrace(); // what happens *after* we reach this line?
        }
    }

    public int [] login() throws RemoteException, SQLException {
        return server.loginUser(this.username, this.password);
    }

    public int register() throws RemoteException, SQLException {
        return server.registerUser(this.username, this.password);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername(){ return this.username; }

    public double getBalance(int userId) throws RemoteException, SQLException {
        return server.getBalance(userId);
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package repositories;

import genericclasses.User;
import rmi.RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

/**
 * Created by mariobalca on 12/16/15.
 */
public class AuthRepository {
    private RMI rmiServer;

    public AuthRepository(){
        try {
            this.rmiServer = (RMI) LocateRegistry.getRegistry("localhost",7000).lookup("rmi");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public User login(String username, String password) throws RemoteException, SQLException {
        int [] login = new int[2];
        try {
            login = rmiServer.loginUser(username, password);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(login[0] >= 1) {
            User user = rmiServer.getUser(login[0]);
            user.setId(login[0]);
            user.setRequests(login[1]);
            return user;
        }
        return null;
    }

    public User register(String username, String password) throws RemoteException, SQLException {
        int userId = 0;
        try {
            userId = rmiServer.registerUser(username, password);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(userId > 1) {
            User user = rmiServer.getUser(userId);
            user.setRequests(0);
            user.setId(userId);
            return user;
        }
        return null;
    }

    public double getBalance(int userId) {
        try {
            return rmiServer.getBalance(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

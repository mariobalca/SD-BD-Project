package repositories;

import genericclasses.User;
import rmi.RMI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mariobalca on 12/16/15.
 */
public class AuthRepository {
    private RMI rmiServer;

    public AuthRepository(){
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
        if(userId >= 1) {
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

    public ArrayList<User> getUsers(){
        try {
            return rmiServer.getUsers();
        } catch (RemoteException e) {
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public String registerTumblr(){
        try {
            return rmiServer.signInTumblr();
        } catch (RemoteException e) {
            return null;
        }
    }

    public Boolean createUserTumblr(String oauth_token,String oauth_verifier){
        try {
            rmiServer.createTumblrUser(oauth_token,oauth_verifier);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

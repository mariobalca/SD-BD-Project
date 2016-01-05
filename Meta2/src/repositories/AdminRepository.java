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
 * Created by pedrocb on 18/12/2015.
 */
public class AdminRepository {
    private RMI rmiServer;
    public AdminRepository(){
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
    public boolean addAdmin(int projectId,int requestId,int userId,String newAdminName){
        try {
            return rmiServer.addAdmin(projectId,requestId,userId,newAdminName);
        } catch (RemoteException e) {
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean removeAdmin(int projectId,int requestId,int userId, int adminId){
        try {
            return rmiServer.removeAdmin(projectId,requestId,userId,adminId);
        } catch (RemoteException e) {
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public ArrayList<User> getAdmins(int projectId){
        try {
            return rmiServer.getAdmins(projectId);
        } catch (RemoteException e) {
            return null;
        } catch (SQLException e) {
            return null;
        }
    }
}

package repositories;

import genericclasses.Extra;
import rmi.RMI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by pedrocb on 17/12/2015.
 */
public class ExtraRepository {
    private RMI rmiServer;

    public ExtraRepository(){
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

    public boolean createExtra(Extra extra,int requestId,int projectId,int userId){
        boolean successful;
        try {
            successful = rmiServer.createExtraLevel(extra,requestId,projectId,userId);
        } catch (RemoteException e) {
            successful = false;
        } catch (SQLException e) {
            successful = false;
        }
        return successful;
    }

    public boolean removeExtra(int extraId, int requestId, int userId){
        boolean successful;
        try {
            successful = rmiServer.removeExtraLevel(extraId,requestId,userId);
        } catch (RemoteException e) {
            successful = false;
        } catch (SQLException e) {
            successful = false;
        }
        return successful;
    }

    public ArrayList<Extra> getProjectExtras(int projectId){
        try {
            return rmiServer.getProjectExtraLevels(projectId);
        } catch (RemoteException e) {
            return null;
        } catch (SQLException e) {
            return null;
        }
    }
}

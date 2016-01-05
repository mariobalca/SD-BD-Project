package repositories;

import genericclasses.Path;
import rmi.RMI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by pedrocb on 18/12/2015.
 */
public class PathRepository {
    private RMI rmiServer;

    public PathRepository(){
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

    public ArrayList<Path> getProjectPaths(int projectId){
        try {
            return rmiServer.getProjectPaths(projectId);
        } catch (RemoteException e) {
            return null;
        } catch (SQLException e) {
            return null;
        }
    }
}

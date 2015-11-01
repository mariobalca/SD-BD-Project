/**
 * Created by mariobalca on 24-10-2015.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;

public class RMIServer{
    String hostname;
    public RMIServer(){
        try {

            try {
                BufferedReader fR = new BufferedReader(new FileReader("RMIServer/config.txt"));
                hostname = fR.readLine();
                fR.close();
            }
            catch (Exception e){
                System.out.println("Erro ao abrir ficheiro client_config");
            }
            Connection connection = DriverManager.getConnection("jdbc:sqlite:RMIServer/src/Database/db.db");
            System.setProperty("java.rmi.server.hostname",hostname);
            RMIImpl rmiServer = new RMIImpl(connection);
            Registry r = LocateRegistry.createRegistry(7000);
            r.rebind("rmi", rmiServer);
            Timer t = new Timer();
            MyTask mTask = new MyTask(rmiServer);
            // This task is scheduled to run every 10 seconds

            t.scheduleAtFixedRate(mTask, 0, 30000);
            System.out.println("RMI Server ready.");
        } catch (RemoteException re) {
            System.out.println("Exception in RMIServer.main: " + re);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new RMIServer();
    }

}

package rmi; /**
 * Created by mariobalca on 24-10-2015.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;

public class RMIServer{
    String hostname;
    int port;
    int delta;
    public RMIServer(){
        try {

            try {
                BufferedReader fR = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream ("configRMI.txt")));
                hostname = fR.readLine();
                port = Integer.parseInt(fR.readLine());
                delta = Integer.parseInt(fR.readLine());
                fR.close();
            }
            catch (Exception e){
                System.out.println("Erro ao abrir ficheiro configRMI");
                System.exit(1);
            }
            Connection connection = DriverManager.getConnection("jdbc:sqlite:RMIServer/src/db/db.db");
            //Connection connection = DriverManager.getConnection("jdbc:sqlite:db/db.db");
            System.setProperty("java.rmi.server.hostname",hostname);
            RMIImpl rmiServer = new RMIImpl(connection);
            Registry r = LocateRegistry.createRegistry(port);
            r.rebind("rmi", rmiServer);
            Timer t = new Timer();
            MyTask mTask = new MyTask(rmiServer);
            // This task is scheduled to run every 10 seconds

            t.scheduleAtFixedRate(mTask, 0, delta);
            System.out.println("rmi.RMI Server ready.");
        } catch (RemoteException re) {
            System.out.println("Port already in use");
            System.exit(1);
        } catch (SQLException e){

        }
    }

    public static void main(String args[]) {
        new RMIServer();
    }

}

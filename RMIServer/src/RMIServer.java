/**
 * Created by mariobalca on 24-10-2015.
 */

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RMIServer{
    public static void main(String args[]) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/Database/db.db");

            RMIImpl rmiServer = new RMIImpl(connection);
            Registry r = LocateRegistry.createRegistry(7000);
            r.rebind("rmi", rmiServer);
            System.out.println("Hello Server ready.");
        } catch (RemoteException re) {
            System.out.println("Exception in RMIServer.main: " + re);
        }
    }

}

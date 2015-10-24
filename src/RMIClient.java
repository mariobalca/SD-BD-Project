/**
 * Created by mariobalca on 24-10-2015.
 */

import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

public class RMIClient {

    public static void main(String args[]) {
        try {
            RMI h = (RMI) LocateRegistry.getRegistry(7000).lookup("rmi");
            ArrayList<User> users = h.getUsers();
            System.out.println("Server: " + users.get(0).toString());
        } catch (Exception e) {
            System.out.println("Exception in main: " + e);
            e.printStackTrace();
        }

    }

}
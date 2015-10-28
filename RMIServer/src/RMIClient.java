/**
 * Created by mariobalca on 24-10-2015.
 */

import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

public class RMIClient {

    public static void main(String args[]) {
        try {
            RMI h = (RMI) LocateRegistry.getRegistry(7000).lookup("rmi");
            ArrayList<User> projects = h.getUsers();
            h.removeProject(1,1,1);
            for(User item : projects){
                System.out.println(item.toString());
            }
        } catch (Exception e) {
            System.out.println("Exception in main: " + e);
            e.printStackTrace();
        }

    }

}
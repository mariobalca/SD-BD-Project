/**
 * Created by mariobalca on 24-10-2015.
 */

import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

public class RMIClient {

    public static void main(String args[]) {
        try {
            RMI h = (RMI) LocateRegistry.getRegistry(7000).lookup("rmi");
            ArrayList<Project> projects = h.getProjects();
            for(Project item : projects){
                System.out.println(item.toString());
            }
        } catch (Exception e) {
            System.out.println("Exception in main: " + e);
            e.printStackTrace();
        }

    }

}
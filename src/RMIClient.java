/**
 * Created by mariobalca on 24-10-2015.
 */

import java.rmi.*;

public class RMIClient {

    public static void main(String args[]) {

		/* This might be necessary if you ever need to download classes:
		System.getProperties().put("java.security.policy", "policy.all");
		System.setSecurityManager(new RMISecurityManager());
		*/

        try {

            RMI h = (RMI) Naming.lookup("rmi://localhost:700");

            String message = h.sayHello();
            System.out.println("HelloClient: " + message);
        } catch (Exception e) {
            System.out.println("Exception in main: " + e);
            e.printStackTrace();
        }

    }

}
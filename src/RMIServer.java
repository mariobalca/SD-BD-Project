/**
 * Created by mariobalca on 24-10-2015.
 */

import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

public class RMIServer extends UnicastRemoteObject implements RMI {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public RMIServer() throws RemoteException {
        super();
    }

    public String sayHello() throws RemoteException {
        System.out.println("print do lado do servidor...!.");

        return "Hello, World!";
    }

    // =========================================================
    public static void main(String args[]) {

        try {
            RMIServer h = new RMIServer();
            Naming.rebind("rmi://localhost:7000/", h);
            System.out.println("Hello Server ready.");
        } catch (RemoteException re) {
            System.out.println("Exception in HelloImpl.main: " + re);
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException in HelloImpl.main: " + e);
        }

    }

}

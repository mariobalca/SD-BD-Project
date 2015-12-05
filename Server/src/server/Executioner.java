package server;

import genericclasses.Request;
import rmi.RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by pedro on 01-11-2015.
 */
public class Executioner extends Thread{
    public Connection connection;
    public RMI rmi;
    public Executioner(Connection connection) {
        this.connection = connection;
        try {
            rmi = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS,Server.rmiPort).lookup("rmi");
        } catch (RemoteException e) {

        } catch (NotBoundException e) {

        }
    }

    @Override
    public void run() {
        while(true){
            Request request = connection.getRequest();
            System.out.println(request.getTipo());
            try {
                rmi = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS,Server.rmiPort).lookup("rmi");
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
            connection.setResponse(request.execute(rmi));
            synchronized (this){
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}

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
            rmi = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS,7000).lookup("rmi");
        } catch (RemoteException e) {
        } catch (NotBoundException e) {
        }
    }

    @Override
    public void run() {
        while(true){
            Request request = connection.getRequest();
            System.out.println(request.getTipo());
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

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

/**
 * Created by Rui on 27/10/2015.
 */
public class CheckBalance extends  Request{

    public CheckBalance(){
        super("CheckBalance");
    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                double value = rmiServer.getBalance(userId);
                return new DoubleResponse("CheckBalance",value);
            } catch (RemoteException e) {
                verifica = false;
                try {
                    rmiServer = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS, 7000).lookup("rmi");
                } catch (RemoteException e1) {

                } catch (NotBoundException e1) {
                    e1.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new DoubleResponse("CheckBalance", 0);
    }

}

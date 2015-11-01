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
<<<<<<< HEAD
                rmiServer = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS,Server.rmiPort).lookup("rmi");
=======
                rmiServer = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS, Server.rmiPort).lookup("rmi");
>>>>>>> dd7e68f70d5714632207eb123ffeee4901e471d7
                double value = rmiServer.getBalance(userId);
                return new DoubleResponse("CheckBalance",value);
            } catch (RemoteException e) {
                verifica = false;
            } catch (SQLException e) {
<<<<<<< HEAD
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
=======

            } catch (NotBoundException e) {

>>>>>>> dd7e68f70d5714632207eb123ffeee4901e471d7
            }
        }

        return new DoubleResponse("CheckBalance", 0);
    }

}

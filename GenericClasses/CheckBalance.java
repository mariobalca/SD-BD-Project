import java.rmi.RemoteException;
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new DoubleResponse("CheckBalance", 0);
    }

}

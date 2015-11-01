import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Rui on 27/10/2015.
 */
public class CheckRewards extends Request {
    public CheckRewards(){
        super("CheckRewards");
    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                ArrayList<Reward> rewardlist = rmiServer.getUserRewards(userId);
                return new RewardsResponse("CheckRewards",rewardlist);
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

        return new RewardsResponse("CheckRewards", new ArrayList<Reward>());
    }
}

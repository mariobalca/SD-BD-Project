import javax.management.remote.rmi.RMIServer;
import java.rmi.RemoteException;
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new RewardsResponse("CheckRewards", new ArrayList<Reward>());
    }
}

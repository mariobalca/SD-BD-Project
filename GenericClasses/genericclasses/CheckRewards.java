package genericclasses;

import rmi.RMI;
import server.Server;

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
                rmiServer = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS, Server.rmiPort).lookup("rmi");
                ArrayList<Reward> rewardlist = rmiServer.getUserRewards(userId);
                return new RewardsResponse("CheckRewards",rewardlist);
            } catch (RemoteException e) {
                verifica = false;
            } catch (SQLException e) {

            } catch (NotBoundException e) {

            }
        }

        return new RewardsResponse("CheckRewards", new ArrayList<Reward>());
    }
}

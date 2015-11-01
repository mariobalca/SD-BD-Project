import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by pedro on 01-11-2015.
 */
public class GiveReward extends Request {

    public int rewardId,requestId;
    public String username;

    public GiveReward(ArrayList<Reward> rewards) {
        super("GiveReward");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("What reward do you want to give? (Insert ID)");

        int check = 0;
        while(check == 0)
        {
            try {
                rewardId = Integer.parseInt(reader.readLine());
                if (rewardId > 0){
                    for (Reward reward : rewards) {
                        if (reward.getId() == rewardId) {
                            check = 1;
                        }
                    }
                }
                if(check == 0)
                    System.out.println("Insert a valid reward");

            } catch (Exception e) {
                continue;
            }
        }

        System.out.println("Which user do you want to give to?");
        try {
            username = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        requestId = ++Client.requestId;
    }
    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                return new BooleanResponse("GiveReward",rmiServer.giveReward(rewardId,requestId,userId,username,0));
            } catch (RemoteException e) {
                verifica = false;
                try {
                    rmiServer = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS, Server.rmiPort).lookup("rmi");
                } catch (RemoteException e1) {

                } catch (NotBoundException e1) {
                    e1.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new BooleanResponse("GiveReward", false);
    }
}

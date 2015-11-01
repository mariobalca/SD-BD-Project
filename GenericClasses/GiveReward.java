import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by pedro on 01-11-2015.
 */
public class GiveReward extends Request {

    public int rewardId,requestId;
    public String username;

    public GiveReward() {
        super("GiveReward");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("What reward do you want to give? (Insert ID)");

        int check = 0;
        while(check == 0)
        {
            try {
                rewardId = Integer.parseInt(reader.readLine());
                if (rewardId <= 0)
                    continue;

            } catch (Exception e) {
                continue;
            }
            check =1;
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new BooleanResponse("GiveReward", false);
    }
}

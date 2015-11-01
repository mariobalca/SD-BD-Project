import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

/**
 * Created by pedro on 01-11-2015.
 */
public class RemoveReward extends Request {
    public int requestId;
    public int proj;
    public int rewardId;

    public RemoveReward(int projectId) {
        super("RemoveReward");
        this.proj = projectId;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean check = true;
        while(check) {
            System.out.println("Write the id of the reward: ");
            try {
                rewardId = Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                System.out.println("Erro de escrita do inteiro.");
                continue;
            }
            check = false;
        }
        requestId = ++Client.requestId;
    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                return new BooleanResponse("RemoveReward",rmiServer.removeReward(rewardId,requestId,userId));
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

        return new BooleanResponse("RemoveReward", false);
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;

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

        //Definir o valor minimo da reward
        System.out.println("Write the id of the reward: ");
        try{
            rewardId = Integer.parseInt(reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita do inteiro.");
        }
        requestId = ++Client.requestId;
    }
}

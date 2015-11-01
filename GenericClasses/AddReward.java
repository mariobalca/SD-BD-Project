import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Rui on 26/10/2015.
 */
public class AddReward extends Request{


    public int proj;
    public double valor;
    public String nome = "";
    public String descricao = "";
    public int requestId;

    public AddReward(int proj){
        super("AddReward");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        this.proj = proj;
        //Definir o valor minimo da reward
        System.out.println("Write the value of the reward: ");
        try{
            valor = Double.parseDouble(reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita do double.");
        }

        //Definir o nome da reward
        System.out.println("Write the reward's name: ");
        try{
           nome = (reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }

        //Definir a descricao da reward
        System.out.println("Write the reward's description: ");
        try{
           descricao = (reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }
        requestId = ++Client.requestId;

    }
}

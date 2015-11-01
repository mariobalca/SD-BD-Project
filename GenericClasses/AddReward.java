import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

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

        boolean check = true;

        while(check) {
            //Definir o valor minimo da reward
            System.out.println("Write the value of the reward: ");
            try {
                valor = Double.parseDouble(reader.readLine());
                if(valor > 0) {

                    System.out.println("Write the reward's name: ");
                    try {
                        nome = (reader.readLine());
                    } catch (Exception e) {
                        System.out.println("Erro de escrita.");
                        continue;
                    }

                    //Definir a descricao da reward
                    System.out.println("Write the reward's description: ");
                    try {
                        descricao = (reader.readLine());
                    } catch (Exception e) {
                        System.out.println("Erro de escrita.");
                        continue;
                    }

                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Erro de escrita do double.");
                continue;
            }




        }
        requestId = ++Client.requestId;

    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                return new BooleanResponse("AddReward",rmiServer.createReward(new Reward(valor,nome,descricao),requestId,proj,userId));
            } catch (RemoteException e) {
                verifica = false;
                try {
                    rmiServer = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS, Server.rmiPort).lookup("rmi");
                } catch (RemoteException e1) {

                } catch (NotBoundException e1) {

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new BooleanResponse("AddReward", false);
    }
}

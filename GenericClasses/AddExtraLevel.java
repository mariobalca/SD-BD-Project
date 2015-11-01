import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

/**
 * Created by mariobalca on 01-11-2015.
 */
public class AddExtraLevel extends Request{

    public int proj;
    public double valor;
    public String nome = "";
    public String descricao = "";
    public int requestId;

    public AddExtraLevel(int proj){
        super("AddExtraLevel");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        this.proj = proj;

        boolean check = true;
        while (check) {
            //Definir o valor minimo da reward
            System.out.println("Write the value of the extra: ");
            try {
                valor = Double.parseDouble(reader.readLine());
                if(valor < 0){
                    //Definir o nome da reward
                    System.out.println("Write the extra's name: ");
                    try {
                        nome = (reader.readLine());
                    } catch (Exception e) {
                        System.out.println("Erro de escrita.");
                    }

                    //Definir a descricao da reward
                    System.out.println("Write the extra's description: ");
                    try {
                        descricao = (reader.readLine());
                    } catch (Exception e) {
                        System.out.println("Erro de escrita.");
                    }
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Erro de escrita do double.");
            }


        }
        requestId = ++Client.requestId;

    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                return new BooleanResponse("AddExtraLevel",rmiServer.createExtraLevel(new Extra(valor, nome, descricao), requestId, proj, userId));
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

        return new BooleanResponse("AddExtraLevel", false);
    }
}

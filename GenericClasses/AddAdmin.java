import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

/**
 * Created by Rui on 26/10/2015.
 */
public class AddAdmin extends Request{
    public int proj;
    public String admin = "";
    public int requestId;

    public AddAdmin(int proj){
        super("AddAdmin");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        this.proj = proj;


        //Definir o username para nomear como admin
        System.out.println("Write the username of the new admin: ");
        try{
            admin = reader.readLine();
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }
        requestId = ++Client.requestId;
    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                return new BooleanResponse("AddAdmin",rmiServer.addAdmin(proj,requestId,userId,admin));
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

        return new BooleanResponse("AddAdmin", false);
    }
}

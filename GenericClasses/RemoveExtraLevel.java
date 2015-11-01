import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by mariobalca on 01-11-2015.
 */
public class RemoveExtraLevel extends Request {
    public int requestId;
    public int proj;
    public int extraId;

    public RemoveExtraLevel(int projectId) {
        super("RemoveExtraLevel");
        this.proj = projectId;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        int check = 0;
        while (check == 0) {
            System.out.println("Write the id of the extra level to be removed: ");
            try {
                extraId = Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                System.out.println("Erro de escrita do inteiro.");
                continue;
            }
            check = 1;
        }
        requestId = ++Client.requestId;
    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                return new BooleanResponse("RemoveExtraLevel",rmiServer.removeExtraLevel(extraId,requestId,userId));
            } catch (RemoteException e) {
                verifica = false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new BooleanResponse("RemoveExtraLevel", false);
    }
}

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mariobalca on 01-11-2015.
 */
public class ListProjectExtraLevels extends Request{
    public int proj;

    public ListProjectExtraLevels(int proj){
        super("ListProjectExtraLevels");
        this.proj = proj;
    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                ArrayList<Extra> extras = rmiServer.getProjectExtraLevels(proj);
                ExtraLevelListResponse response = new ExtraLevelListResponse("ListProjectExtraLevels", extras);
                return response;
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

        return new ExtraLevelListResponse("ListProjectExtraLevels", new ArrayList<Extra>());
    }
}

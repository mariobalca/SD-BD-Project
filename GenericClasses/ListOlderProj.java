import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Rui on 26/10/2015.
 */
public class ListOlderProj extends Request{
    public ListOlderProj(){
        super("ListOlderProj");
    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                rmiServer = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS, Server.rmiPort).lookup("rmi");
                ArrayList<Project> projects = rmiServer.getOlderProjects();
                ProjectListResponse response = new ProjectListResponse("ListOldProj", projects);
                return response;
            } catch (RemoteException e) {
                verifica = false;
            } catch (SQLException e) {

            } catch (NotBoundException e) {

            }
        }

        return new ProjectListResponse("ListOldProj", new ArrayList<Project>());
    }
}

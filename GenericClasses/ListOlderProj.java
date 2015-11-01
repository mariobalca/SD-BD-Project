import java.rmi.RemoteException;
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
                ArrayList<Project> projects = rmiServer.getOlderProjects();
                ProjectListResponse response = new ProjectListResponse("ListOldProj", projects);
                return response;
            } catch (RemoteException e) {
                verifica = false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new ProjectListResponse("ListOldProj", new ArrayList<Project>());
    }
}

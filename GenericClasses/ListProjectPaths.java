import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mariobalca on 01-11-2015.
 */
public class ListProjectPaths extends Request {
    public int proj;

    public ListProjectPaths(int proj){
        super("ListProjectPaths");
        this.proj = proj;
    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                ArrayList<Path> paths = rmiServer.getProjectPaths(proj);
                PathListResponse response = new PathListResponse("ListProjectPaths", paths);
                return response;
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

        return new PathListResponse("ListProjectPaths", new ArrayList<Path>());
    }
}

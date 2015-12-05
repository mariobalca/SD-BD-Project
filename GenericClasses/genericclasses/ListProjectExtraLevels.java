package genericclasses;

import rmi.RMI;
import server.Server;

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
                rmiServer = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS, Server.rmiPort).lookup("rmi");
                ArrayList<Extra> extras = rmiServer.getProjectExtraLevels(proj);
                ExtraLevelListResponse response = new ExtraLevelListResponse("ListProjectExtraLevels", extras);
                return response;
            } catch (RemoteException e) {
                verifica = false;
            } catch (SQLException e) {

            } catch (NotBoundException e) {

            }
        }

        return new ExtraLevelListResponse("ListProjectExtraLevels", new ArrayList<Extra>());
    }
}

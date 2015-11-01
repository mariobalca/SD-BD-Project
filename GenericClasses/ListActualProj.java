import com.sun.org.apache.xpath.internal.res.XPATHErrorResources_ja;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Rui on 26/10/2015.
 */
public class ListActualProj extends Request{
    public ListActualProj(){
        super("ListActualProj");
    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                ArrayList<Project> projects = rmiServer.getProjects();
                ProjectListResponse response = new ProjectListResponse("ListActualProj", projects);
                return response;
            } catch (RemoteException e) {
                verifica = false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new ProjectListResponse("ListActualProj", new ArrayList<Project>());
    }
}

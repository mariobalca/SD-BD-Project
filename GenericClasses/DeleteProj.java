import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by Rui on 27/10/2015.
 */
public class DeleteProj extends Request{
    public int proj;
    public int requestId;
    public DeleteProj(int projectId) {
        super("DeleteProj");

        this.proj = projectId;
        requestId = ++Client.requestId;
    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                return new BooleanResponse("DeleteProj",rmiServer.cancelProject(proj, requestId, userId));
            } catch (RemoteException e) {
                verifica = false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new BooleanResponse("CommentProj", false);
    }
}

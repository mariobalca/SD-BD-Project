import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mariobalca on 01-11-2015.
 */
public class ListProjectMessages extends Request {
    public int proj;

    public ListProjectMessages(int proj){
        super("ListProjectMessages");
        this.proj = proj;
    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                ArrayList<Message> messages = rmiServer.getProjectMessages(proj);
                MessageListResponse response = new MessageListResponse("ListProjectMessages", messages);
                return response;
            } catch (RemoteException e) {
                verifica = false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new MessageListResponse("ListProjectMessages", new ArrayList<Message>());
    }
}

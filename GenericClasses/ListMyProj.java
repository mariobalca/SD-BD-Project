/**
 * Created by pedro on 31-10-2015.
 */
public class ListMyProj extends Request {
    public int userId;

    public ListMyProj() {
        super("ListMyProj");
        userId = Client.userId;
    }
}

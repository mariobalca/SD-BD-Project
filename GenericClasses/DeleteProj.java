import java.io.BufferedReader;
import java.io.InputStreamReader;

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
}

import java.util.ArrayList;

/**
 * Created by mariobalca on 01-11-2015.
 */
public class PathListResponse extends Response {
    public ArrayList<Path> paths;
    public PathListResponse(String response, ArrayList<Path> paths) {
        super(response);
        this.paths = paths;
    }
}

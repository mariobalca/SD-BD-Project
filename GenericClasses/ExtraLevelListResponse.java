import java.util.ArrayList;

/**
 * Created by mariobalca on 01-11-2015.
 */
public class ExtraLevelListResponse extends Response{
    public ArrayList<Extra> extras;
    public ExtraLevelListResponse(String response, ArrayList<Extra> extras) {
        super(response);
        this.extras = extras;
    }
}

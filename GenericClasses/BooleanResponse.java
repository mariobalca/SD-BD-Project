/**
 * Created by pedro on 27-10-2015.
 */
public class BooleanResponse extends Response {
    public boolean status;
    public BooleanResponse(String response, Boolean status) {
        super(response);
        this.status = status;
    }

}

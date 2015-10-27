/**
 * Created by pedro on 27-10-2015.
 */
public class StringResponse extends Response{
    private String string;
    public StringResponse(String response,String string) {
        super(response);
        this.string = string;

    }
}

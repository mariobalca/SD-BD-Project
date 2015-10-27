import java.io.Serializable;

/**
 * Created by Rui on 26/10/2015.
 */
public class RequestResponse implements Serializable{
    public Request request;
    public Response response;


    private static int currentId = 0;

    private int id;

    public RequestResponse(Request tipo){
        request = tipo;
        id = currentId;
        currentId++;
    }






}

/**
 * Created by Rui on 26/10/2015.
 */
public class RequestResponse {
    Request request;
    Response response;

    int id;

    RequestResponse(String tipo){
        request = new Request(tipo);
    }




}

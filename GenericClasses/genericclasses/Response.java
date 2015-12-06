package genericclasses;

import java.io.Serializable;

/**
 * Created by Rui on 26/10/2015.
 */
public class Response implements Serializable{
    public String tipo;
    public Response(String response){
        this.tipo = response;
    }
}

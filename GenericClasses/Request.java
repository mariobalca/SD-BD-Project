import java.io.Serializable;

/**
 * Created by Rui on 26/10/2015.
 */


public abstract class Request implements Serializable {

    //protected BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    protected String tipo;
    protected int userId;

    public Request(String tipo) {
        this.tipo = tipo;
        this.userId = Client.userId;
    }

    public String getTipo() {
        return tipo;
    }

    public Response execute(RMI rmiServer){
        System.out.println("executei");
        return new Response("cona");
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rui on 26/10/2015.
 */

import java.util.ArrayList;


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

    public void awnser(IOThread thread) {
    }
}

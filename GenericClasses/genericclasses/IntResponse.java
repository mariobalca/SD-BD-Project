package genericclasses;

/**
 * Created by pedro on 28-10-2015.
 */
public class IntResponse extends Response{
    public int[] values;
    public IntResponse(String tipo,int[] values){
        super(tipo);
        this.values = values;
    }
}
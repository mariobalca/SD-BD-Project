/**
 * Created by pedro on 30-10-2015.
 */
public class DoubleResponse extends Response{
    public double value;
    public DoubleResponse(String tipo, double value){
        super(tipo);
        this.value = value;
    }
}

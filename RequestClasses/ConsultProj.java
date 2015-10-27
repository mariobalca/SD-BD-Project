import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Rui on 26/10/2015.
 */
public class ConsultProj extends Request{
    private int proj;
    public ConsultProj(){
        super("ConsultProj");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Definir o projeto a consultar
        System.out.println("Indique o ID do projeto a consultar: ");
        try{
            proj = Integer.parseInt(reader.readLine());
        }
        catch (Exception e) {
        }
    }
}

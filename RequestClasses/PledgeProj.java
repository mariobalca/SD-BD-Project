import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Rui on 27/10/2015.
 */
public class PledgeProj extends Request {
    private int proj, path;
    private double valor;

    public PledgeProj(){
        super("PledgeProj");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        //Definir o projeto a apoiar
        System.out.println("Indique o ID do projeto: ");
        try {
            proj = Integer.parseInt(reader.readLine());
        }
        catch (Exception e) {
        }

        //Definir a quantia com que apoiar o projeto
        System.out.println("Indique com quanto quer apoiar o projecto: ");
        try{
           valor = (Double.parseDouble(reader.readLine()));
        }
        catch (Exception e) {
        }

        //Escolher o caminho (voto) para o projeto
        System.out.println("Indique em qual caminho quer votar (id do path): ");
        try{
            path = (Integer.parseInt(reader.readLine()));
        }
        catch (Exception e) {
        }
    }
}

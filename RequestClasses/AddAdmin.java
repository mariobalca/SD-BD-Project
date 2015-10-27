import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Rui on 26/10/2015.
 */
public class AddAdmin extends Request{
    private int proj;
    private String admin = "";

    public AddAdmin(){
        super("AddAdmin");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Definir o projeto a que adicionar o admin
        System.out.println("Indique o ID do projeto: ");
        try{
            proj = Integer.parseInt(reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }


        //Definir o username para nomear como admin
        System.out.println("Indique o username para ser adicionado como admin: ");
        try{
            admin = reader.readLine();
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }
    }
}

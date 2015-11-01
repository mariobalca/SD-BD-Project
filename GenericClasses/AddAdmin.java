import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Rui on 26/10/2015.
 */
public class AddAdmin extends Request{
    public int proj;
    public String admin = "";
    public int requestId;

    public AddAdmin(int proj){
        super("AddAdmin");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        this.proj = proj;


        //Definir o username para nomear como admin
        System.out.println("Write the username of the new admin: ");
        try{
            admin = reader.readLine();
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }
        requestId = ++Client.requestId;
    }
}

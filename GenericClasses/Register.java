import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Rui on 26/10/2015.
 */
public class Register extends Request{
    public String username = "";
    public String password = "";


    public Register() {
        super("Register");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //add username;
        System.out.println("Insira o Username: ");
        try{
            username = reader.readLine();
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }
        //add password;
        System.out.println("Insira a Password: ");
        try{
            password = reader.readLine();
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }
    }

    @Override
    public void awnser(IOThread thread) {
        IntResponse intResponse = (IntResponse) Client.currentRequest.response;
        Client.userId = intResponse.values[0];
        if(Client.userId == 0){
            System.out.println("Invalid Register");
        }
        else {
            System.out.println("Successful Register");
        }
    }
}

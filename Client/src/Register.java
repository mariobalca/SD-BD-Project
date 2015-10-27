/**
 * Created by Rui on 26/10/2015.
 */
public class Register extends Request{
    Register(){
        //add username;
        System.out.println("Insira o Username: ");
        try{
            campos_string.add(reader.readLine());
        }
        catch (Exception e) {
        }
        //add password;
        System.out.println("Insira a Password: ");
        try{
            campos_string.add(reader.readLine());
        }
        catch (Exception e) {
        }
    }
}

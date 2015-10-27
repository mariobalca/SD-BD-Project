/**
 * Created by Rui on 26/10/2015.
 */
public class AddAdmin extends Request{
    AddAdmin(){
        //Definir o projeto a que adicionar o admin
        System.out.println("Indique o ID do projeto: ");
        try{
            campos_string.add(reader.readLine());
        }
        catch (Exception e) {
        }
        //Definir o username para nomear como admin
        System.out.println("Indique o username para ser adicionado como admin: ");
        try{
            campos_string.add(reader.readLine());
        }
        catch (Exception e) {
        }
    }
}

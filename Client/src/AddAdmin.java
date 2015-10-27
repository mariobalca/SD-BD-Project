/**
 * Created by Rui on 26/10/2015.
 */
public class AddAdmin extends Request{
    AddAdmin(){
        int proj;
        String admin = "";

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

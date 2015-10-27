/**
 * Created by Rui on 26/10/2015.
 */
public class ConsultProj extends Request{
    int proj;
    ConsultProj(){
        //Definir o projeto a consultar
        System.out.println("Indique o ID do projeto a consultar: ");
        try{
            proj = Integer.parseInt(reader.readLine());
        }
        catch (Exception e) {
        }
    }
}

/**
 * Created by Rui on 26/10/2015.
 */
public class ConsultProj extends Request{

    ConsultProj(){
        //Definir o projeto a consultar
        System.out.println("Indique o ID do projeto: ");
        campos_string.add(reader.realLine());
    }
}

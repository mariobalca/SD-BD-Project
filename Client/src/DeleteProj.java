/**
 * Created by Rui on 27/10/2015.
 */
public class DeleteProj extends Request{
    
    DeleteProj() {
        //Definir o projeto a eliminar
        System.out.println("Indique o ID do projeto a eliminar: ");
        try {
            campos_string.add(reader.readLine());
        } catch (Exception e) {
        }
    }
}

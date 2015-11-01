import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Rui on 27/10/2015.
 */
public class DeleteProj extends Request{
    private int proj;
    public int requestId;
    public DeleteProj() {
        super("DeleteProj");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Definir o projeto a eliminar
        System.out.println("Write the project Id to delete: ");
        try {
            proj = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
        }
        requestId = ++Client.requestId;
    }
}

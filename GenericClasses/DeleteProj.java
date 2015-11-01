import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Rui on 27/10/2015.
 */
public class DeleteProj extends Request{
    public int proj;
    public int requestId;
    public DeleteProj() {
        super("DeleteProj");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        int check = 0;
        while(check == 0)
        {
            try {

                //Definir o projeto a eliminar
                System.out.println("Write the project Id to delete: ");
                proj = Integer.parseInt(reader.readLine());
                if (proj<=0){
                    continue;
                }

            } catch (Exception e) {
                continue;
            }
            check =1;
        }


        requestId = ++Client.requestId;
    }
}

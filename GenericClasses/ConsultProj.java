import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Rui on 26/10/2015.
 */
public class ConsultProj extends Request{
    private int proj;
    public ConsultProj(){
        super("ConsultProj");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Definir o projeto a consultar
        System.out.println("Indique o ID do projeto a consultar: ");
        try{
            proj = Integer.parseInt(reader.readLine());
        }
        catch (Exception e) {
        }
    }

    @Override
    public void awnser(IOThread thread) {
        ProjectListResponse projectListResponse = (ProjectListResponse) Client.currentRequest.response;
        if (projectListResponse.projects.size() == 0) {
            System.out.println("Nothing to show");
        } else {
            for (Project project : projectListResponse.projects) {
                System.out.println(project);
            }
            thread.schedule(new ConsultProj());
        }
    }
}

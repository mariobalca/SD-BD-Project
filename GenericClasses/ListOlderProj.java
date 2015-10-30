/**
 * Created by Rui on 26/10/2015.
 */
public class ListOlderProj extends Request{
    public ListOlderProj(){
        super("ListOlderProj");
    }

    @Override
    public void awnser(IOThread thread){
        ProjectListResponse projectListResponse = (ProjectListResponse)Client.currentRequest.response;
        if(projectListResponse.projects.size() == 0){
            System.out.println("Nothing to show");
        }
        else{
            for(Project project:projectListResponse.projects){
                System.out.println(project);
            }
            thread.schedule(new ConsultProj());
        }
    }
}

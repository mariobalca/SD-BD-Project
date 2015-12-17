package actions.Project;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.JsonResponse;
import genericclasses.Project;
import repositories.ProjectRepository;

/**
 * Created by pedrocb on 17/12/2015.
 */
public class CreateProjectAction extends ActionSupport {
    private Project project = new Project();
    private int requestId;
    private int userId;
    private JsonResponse response;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public JsonResponse getResponse() {
        return response;
    }

    public void setResponse(JsonResponse response) {
        this.response = response;
    }

    public String execute(){
        ProjectRepository projectRepository = new ProjectRepository();
        if(projectRepository.createProject(project,requestId,userId)){
            response.setSuccess(true);
        }else{
            response.setSuccess(false);
        }
        return SUCCESS;
    }

}

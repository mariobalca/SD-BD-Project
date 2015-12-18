package actions.Project;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.JsonResponse;
import repositories.ProjectRepository;

/**
 * Created by pedrocb on 17/12/2015.
 */
public class CancelProjectAction extends ActionSupport{
    private int projectId;
    private int userId;
    private int requestId;

    private JsonResponse response;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public JsonResponse getResponse() {
        return response;
    }

    public void setResponse(JsonResponse response) {
        this.response = response;
    }

    public String execute(){
        ProjectRepository projectRepository = new ProjectRepository();
        response.setSuccess(projectRepository.deleteProject(projectId,requestId,userId));
        return SUCCESS;
    }
}

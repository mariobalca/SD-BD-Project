package actions.Project;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.JsonResponse;
import repositories.ProjectRepository;

/**
 * Created by pedrocb on 17/12/2015.
 */
public class FinanceProjectAction extends ActionSupport{
    private int projectId;
    private int requestId;
    private int userId;
    private int pathId;
    private double value;
    private JsonResponse response = new JsonResponse();

    public String execute(){
        ProjectRepository projectRepository = new ProjectRepository();
        response.setSuccess(projectRepository.financeProject(projectId,requestId,userId,pathId,value));
        return SUCCESS;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}

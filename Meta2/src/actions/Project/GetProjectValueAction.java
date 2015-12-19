package actions.Project;

import com.opensymphony.xwork2.ActionSupport;
import repositories.ProjectRepository;

/**
 * Created by pedrocb on 19/12/2015.
 */
public class GetProjectValueAction extends ActionSupport{
    private int projectId;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    private double value;

    public String execute(){
        ProjectRepository projectRepository = new ProjectRepository();
        value =  projectRepository.getProjectValue(projectId);
        return SUCCESS;
    }
}

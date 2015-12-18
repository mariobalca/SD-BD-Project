package actions.Project;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.Project;
import repositories.ProjectRepository;

import java.util.ArrayList;

/**
 * Created by mariobalca on 12/18/15.
 */
public class GetProjectAction extends ActionSupport {
    private Project project;
    private int projectId;

    public String execute(){
        ProjectRepository projectRepository = new ProjectRepository();
        project = projectRepository.getProject(projectId);
        return SUCCESS;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
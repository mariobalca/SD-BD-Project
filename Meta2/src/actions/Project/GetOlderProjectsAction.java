package actions.Project;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.Project;
import repositories.ProjectRepository;

import java.util.ArrayList;

/**
 * Created by pedrocb on 17/12/2015.
 */
public class GetOlderProjectsAction extends ActionSupport {
    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public ArrayList<Project> projects;
    public String execute(){
        ProjectRepository projectRepository = new ProjectRepository();
        projects = projectRepository.getOlderProjects();
        return SUCCESS;
    }
}

package actions.Project;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.JsonResponse;
import genericclasses.Project;
import repositories.ProjectRepository;

import java.util.ArrayList;

/**
 * Created by pedrocb on 17/12/2015.
 */
public class GetCurrentProjectsAction extends ActionSupport{
    ArrayList<Project> projects = new ArrayList<Project>();

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }
    public String execute(){
        ProjectRepository projectRepository = new ProjectRepository();
        projects = projectRepository.getCurrentProjects();
        return SUCCESS;
    }
}

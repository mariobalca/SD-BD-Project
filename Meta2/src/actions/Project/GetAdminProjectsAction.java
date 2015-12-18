package actions.Project;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.Project;
import org.omg.PortableInterceptor.SUCCESSFUL;
import repositories.ProjectRepository;

import java.util.ArrayList;

/**
 * Created by pedrocb on 17/12/2015.
 */
public class GetAdminProjectsAction extends ActionSupport{
    private int userId;
    private ArrayList<Project> projects;

    public String execute(){
        ProjectRepository projectRepository = new ProjectRepository();
        projects = projectRepository.getAdminProjects(userId);
        return SUCCESS;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }
}

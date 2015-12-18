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
}

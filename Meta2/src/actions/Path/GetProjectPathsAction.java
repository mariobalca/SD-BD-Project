package actions.Path;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.Path;
import repositories.PathRepository;

import java.util.ArrayList;

/**
 * Created by pedrocb on 18/12/2015.
 */
public class GetProjectPathsAction extends ActionSupport{
    private int projectId;
    private ArrayList<Path> paths;

    public String execute(){
        PathRepository pathRepository = new PathRepository();
        paths = pathRepository.getProjectPaths(projectId);
        return SUCCESS;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }

    public void setPaths(ArrayList<Path> paths) {
        this.paths = paths;
    }
}

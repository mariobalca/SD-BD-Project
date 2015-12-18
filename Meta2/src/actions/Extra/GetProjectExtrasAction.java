package actions.Extra;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.Extra;
import repositories.ExtraRepository;

import java.util.ArrayList;

/**
 * Created by pedrocb on 18/12/2015.
 */
public class GetProjectExtrasAction extends ActionSupport{
    private int projectId;

    public ArrayList<Extra> getExtras() {
        return extras;
    }

    public void setExtras(ArrayList<Extra> extras) {
        this.extras = extras;
    }

    private ArrayList<Extra> extras;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String execute(){
        ExtraRepository extraRepository = new ExtraRepository();
        extras = extraRepository.getProjectExtras(projectId);
        return SUCCESS;
    }
}

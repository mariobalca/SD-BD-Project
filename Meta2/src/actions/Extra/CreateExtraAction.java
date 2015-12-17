package actions.Extra;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.Extra;
import org.apache.struts2.interceptor.SessionAware;
import repositories.ExtraRepository;

import java.util.Map;

/**
 * Created by pedrocb on 17/12/2015.
 */
public class CreateExtraAction extends ActionSupport {
    private Extra extra = new Extra();
    private int requestId;
    private int userId;
    private int projectId;

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

    public String execute(){
        ExtraRepository extraRepository = new ExtraRepository();
        if(extraRepository.createExtra(extra,requestId,projectId,userId)){
            return SUCCESS;
        }
        else{
            return ERROR;
        }
    }
}

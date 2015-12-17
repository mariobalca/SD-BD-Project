package actions.Extra;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import repositories.ExtraRepository;

import java.util.Map;

/**
 * Created by pedrocb on 17/12/2015.
 */
public class DeleteExtraAction extends ActionSupport {
    private int extraId;
    private int requestId;
    private int projectId;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExtraId() {
        return extraId;
    }

    public void setExtraId(int extraId) {
        this.extraId = extraId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String execute(){
        ExtraRepository extraRepository = new ExtraRepository();
        if(extraRepository.removeExtra(extraId,requestId,userId)){
            return SUCCESS;
        }

        else{
            return ERROR;
        }
    }
}

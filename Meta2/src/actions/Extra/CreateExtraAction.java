package actions.Extra;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.Extra;
import genericclasses.JsonResponse;
import org.apache.struts2.interceptor.SessionAware;
import repositories.ExtraRepository;

import java.util.Map;

/**
 * Created by pedrocb on 17/12/2015.
 */
public class CreateExtraAction extends ActionSupport {
    private Extra extra = new Extra();
    private int requestId;
    private JsonResponse response = new JsonResponse();

    public Extra getExtra() {
        return extra;
    }

    public void setExtra(Extra extra) {
        this.extra = extra;
    }

    public JsonResponse getResponse() {
        return response;
    }

    public void setResponse(JsonResponse response) {
        this.response = response;
    }

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
        response.setSuccess(extraRepository.createExtra(extra,requestId,projectId,userId));
        return SUCCESS;
    }
}

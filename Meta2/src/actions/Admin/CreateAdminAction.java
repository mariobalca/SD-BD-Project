package actions.Admin;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.JsonResponse;
import repositories.AdminRepository;

/**
 * Created by pedrocb on 18/12/2015.
 */
public class CreateAdminAction extends ActionSupport {

    private int projectId;
    private int requestId;
    private int userId;
    private String newAdminName;
    private JsonResponse response = new JsonResponse();

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

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

    public String getNewAdminName() {
        return newAdminName;
    }

    public void setNewAdminName(String newAdminName) {
        this.newAdminName = newAdminName;
    }

    public JsonResponse getResponse() {
        return response;
    }

    public void setResponse(JsonResponse response) {
        this.response = response;
    }

    public String execute(){
        AdminRepository adminRepository = new AdminRepository();
        response.setSuccess(adminRepository.addAdmin(projectId,requestId,userId,newAdminName));
        return SUCCESS;
    }
}

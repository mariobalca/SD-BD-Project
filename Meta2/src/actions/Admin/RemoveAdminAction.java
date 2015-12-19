package actions.Admin;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.JsonResponse;
import repositories.AdminRepository;

/**
 * Created by mariobalca on 12/19/15.
 */
public class RemoveAdminAction extends ActionSupport {

    private int projectId;
    private int requestId;
    private int userId;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    private int adminId;
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

    public JsonResponse getResponse() {
        return response;
    }

    public void setResponse(JsonResponse response) {
        this.response = response;
    }

    public String execute(){
        AdminRepository adminRepository = new AdminRepository();
        response.setSuccess(adminRepository.removeAdmin(projectId,requestId,userId,adminId));
        return SUCCESS;
    }
}

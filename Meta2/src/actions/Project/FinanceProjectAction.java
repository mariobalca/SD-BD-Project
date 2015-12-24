package actions.Project;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.JsonResponse;
import genericclasses.User;
import repositories.AdminRepository;
import repositories.AuthRepository;
import repositories.ProjectRepository;
import websockets.WebSocketAnnotation;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by pedrocb on 17/12/2015.
 */
public class FinanceProjectAction extends ActionSupport{
    private int projectId;
    private int requestId;
    private int userId;
    private int pathId;
    private double value;

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

    public int getPathId() {
        return pathId;
    }

    public void setPathId(int pathId) {
        this.pathId = pathId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public JsonResponse getResponse() {
        return response;
    }

    public void setResponse(JsonResponse response) {
        this.response = response;
    }

    private JsonResponse response = new JsonResponse();

    public String execute(){
        ProjectRepository projectRepository = new ProjectRepository();
        AdminRepository adminRepository = new AdminRepository();
        AuthRepository authRepository = new AuthRepository();
        boolean success = projectRepository.financeProject(projectId,requestId,userId,pathId,value);
        double projectValue = projectRepository.getProjectValue(projectId);
        if(success) {
            ArrayList<User> admins = adminRepository.getAdmins(projectId);
            for (User admin : admins) {
                try {
                    Session session = WebSocketAnnotation.wsClients.get(admin.getId());
                    if(session != null)
                        session.getBasicRemote().sendText("{\"action\": \"pledge\", \"projectId\": " + projectId + ", \"value\": " + value + ", \"projectValue\": " + projectValue + "}");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ArrayList<User> users = authRepository.getUsers();
            for (User user : users) {
                try {
                    Session session = WebSocketAnnotation.wsClients.get(user.getId());
                    if(session != null)
                        session.getBasicRemote().sendText("{\"action\": \"newValue\", \"projectId\": " + projectId + ", \"value\": " + value + ", \"projectValue\": " + projectValue + "}");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        response.setSuccess(success);
        return SUCCESS;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}

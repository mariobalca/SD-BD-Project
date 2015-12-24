package actions.Message;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.JsonResponse;
import genericclasses.Message;
import genericclasses.User;
import repositories.AdminRepository;
import repositories.MessageRepository;
import websockets.WebSocketAnnotation;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Rui on 18/12/2015.
 */
public class SendMessageAction extends ActionSupport {
    private Message message = new Message();
    private int projId, requestId;
    private JsonResponse response = new JsonResponse();

    public String execute(){
        MessageRepository messageRepository = new MessageRepository();
        AdminRepository adminRepository = new AdminRepository();
        boolean success = messageRepository.sendMessage(message,projId,requestId);
        if(success){
            ArrayList<User> admins = adminRepository.getAdmins(projId);
            for (User admin: admins) {
                try {
                    Session session = WebSocketAnnotation.wsClients.get(admin.getId());
                    if(session != null)
                        session.getBasicRemote().sendText("{\"action\": \"message\", \"projectId\": " + projId + "}");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        response.setSuccess(success);
        return SUCCESS;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public int getProjId() {
        return projId;
    }

    public void setProjId(int projId) {
        this.projId = projId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public JsonResponse getResponse() {
        return response;
    }

    public void setResponse(JsonResponse response) {
        this.response = response;
    }
}

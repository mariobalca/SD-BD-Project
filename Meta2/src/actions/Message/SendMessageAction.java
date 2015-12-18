package actions.Message;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.JsonResponse;
import genericclasses.Message;
import repositories.MessageRepository;

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
        response.setSuccess(messageRepository.sendMessage(message,projId,requestId));
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

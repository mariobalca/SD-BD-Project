package actions.Message;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.JsonResponse;
import genericclasses.Message;
import genericclasses.MessageListResponse;
import repositories.MessageRepository;

import java.util.ArrayList;

/**
 * Created by Rui on 18/12/2015.
 */
public class GetProjMessageAction extends ActionSupport{
    private ArrayList<Message> messages;
    private int projId;
    private JsonResponse response;

    public String execute(){
        MessageRepository messageRepository = new MessageRepository();
        messages = messageRepository.getProjectMessages(projId);
        return SUCCESS;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public int getProjId() {
        return projId;
    }

    public void setProjId(int projId) {
        this.projId = projId;
    }

    public JsonResponse getResponse() {
        return response;
    }

    public void setResponse(JsonResponse response) {
        this.response = response;
    }
}

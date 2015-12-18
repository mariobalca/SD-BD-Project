package actions.Message;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.JsonResponse;
import genericclasses.Message;
import repositories.MessageRepository;

/**
 * Created by Rui on 18/12/2015.
 */
public class AnswerMessageAction extends ActionSupport{
    private int userId, requestId, messageId;
    private JsonResponse response = new JsonResponse();
    private String resposta;

    public String execute(){
        MessageRepository messageRepository = new MessageRepository();
        response.setSuccess(messageRepository.answerMessage(messageId,resposta,requestId,userId));
        return SUCCESS;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public JsonResponse getResponse() {
        return response;
    }

    public void setResponse(JsonResponse response) {
        this.response = response;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
}

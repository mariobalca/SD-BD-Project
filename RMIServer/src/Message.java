import java.io.Serializable;

/**
 * Created by mariobalca on 25-10-2015.
 */
public class Message implements Serializable {
    private int id;
    private String subject;
    private String question;
    private String response;
    private String sender;
    private String receiver;

    public Message(int id, String sub, String q, String res, String sen, String rec){
        this.id = id;
        this.subject = sub;
        this.question = q;
        this.response = res;
        this.receiver = rec;
        this.sender = sen;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getId() {
        return id;
    }
}

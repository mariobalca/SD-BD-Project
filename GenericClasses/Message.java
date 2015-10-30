import java.io.Serializable;

/**
 * Created by mariobalca on 25-10-2015.
 */
public class Message implements Serializable {
    private int id;
    private String subject;
    private String question;
    private String response;
    private User sender;


    public Message(String sub, String q, String res, User sen){
        this.subject = sub;
        this.question = q;
        this.response = res;
        this.sender = sen;
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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setId(int id) {
        this.id = id;
    }
}

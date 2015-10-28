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
    private User receiver;


    public Message(int id, String sub, String q, String res, User sen, User rec){
        this.id = id;
        this.subject = sub;
        this.question = q;
        this.response = res;
        this.sender = sen;
        this.receiver = rec;
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

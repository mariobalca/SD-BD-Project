package genericclasses;

import java.io.Serializable;

/**
 * Created by mariobalca on 25-10-2015.
 */
public class Message implements Serializable {
    private int id;
    private String subject;
    private String question;
    private String response;
    private int fromUserId;
    private String fromUserUsername;


    public Message(String sub, String q, int fromUserId){
        this.subject = sub;
        this.question = q;
        this.fromUserId = fromUserId;
    }

    public Message(String sub, String q, String r){
        this.subject = sub;
        this.question = q;
        this.response = r;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserUsername() {
        return fromUserUsername;
    }

    public void setFromUserUsername(String fromUserUsername) {
        this.fromUserUsername = fromUserUsername;
    }

    @Override
    public String toString() {
        return "Message Id: " + id + "Question by: "+fromUserUsername+" Subject: " + subject + " \nQuestion: " + question+ " \nResponse: " + response;
    }
}

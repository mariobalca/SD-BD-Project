package genericclasses;

import java.util.ArrayList;

/**
 * Created by mariobalca on 01-11-2015.
 */
public class MessageListResponse extends Response {
    public ArrayList<Message> messages;
    public MessageListResponse(String response, ArrayList<Message> messages) {
        super(response);
        this.messages = messages;
    }
}

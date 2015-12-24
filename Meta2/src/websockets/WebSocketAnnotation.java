package websockets;

import genericclasses.User;
import org.apache.struts2.interceptor.SessionAware;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnOpen;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.Session;

@ServerEndpoint("/endpoint/{userId}")

public class WebSocketAnnotation {

    static public HashMap<Integer, Session> wsClients = new HashMap<>();

    @OnOpen
    public void start(Session session, @PathParam("userId") int userId) {
        wsClients.put(userId, session);
        System.out.println("User " + userId + " connected");
    }

    @OnClose
    public void end() {
        // clean up once the WebSocket connection is closed
    }

    @OnMessage
    public void receiveMessage(String message, @PathParam("userId") int userId) {
        sendMessage("Olá " + userId, userId);
    }

    @OnError
    public void handleError(Throwable t) {
        t.printStackTrace();
    }

    private void sendMessage(String text, int userId) {
        // uses *this* object's session to call sendText()
        try {
            wsClients.get(userId).getBasicRemote().sendText(text);
        } catch (IOException e) {
            // clean up once the WebSocket connection is closed
            try {
                this.wsClients.get(userId).close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}

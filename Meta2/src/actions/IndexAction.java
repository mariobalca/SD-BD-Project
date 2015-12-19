package actions;

import com.opensymphony.xwork2.ActionSupport;
import websockets.WebSocketAnnotation;

/**
 * Created by mariobalca on 12/19/15.
 */
public class IndexAction extends ActionSupport {

    @Override
    public String execute(){
        return SUCCESS;
    }
}

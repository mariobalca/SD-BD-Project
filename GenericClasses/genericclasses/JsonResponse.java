package genericclasses;

import java.io.Serializable;

/**
 * Created by mariobalca on 12/16/15.
 */
public class JsonResponse implements Serializable{
    private boolean success;
    private String message;
    public JsonResponse(){
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

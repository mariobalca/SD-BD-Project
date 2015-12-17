package actions.Auth;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.JsonResponse;
import genericclasses.Login;
import genericclasses.User;
import org.apache.struts2.interceptor.SessionAware;

import java.rmi.RemoteException;
import java.sql.SQLException;

import repositories.AuthRepository;
import java.util.Map;

/**
 * Created by pedro on 12/4/15.
 */

public class LoginAction extends ActionSupport implements SessionAware{
    private Map<String, Object> session;
    private JsonResponse response = new JsonResponse();
    private User user = new User();

    @Override
    public String execute() throws RemoteException, SQLException {
        AuthRepository auth = new AuthRepository();
        user = auth.login(user.getUsername(), user.getPassword());
        if(user == null) {
            response.setSuccess(false);
            response.setMessage("Dados de login errados");
        }
        else {
            response.setSuccess(true);
            session.put("user", user);
        }
        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public JsonResponse getResponse() {
        return response;
    }

    public void setResponse(JsonResponse response) {
        this.response = response;
    }
}

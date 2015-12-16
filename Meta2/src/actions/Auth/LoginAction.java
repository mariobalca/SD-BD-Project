package actions.Auth;

import com.opensymphony.xwork2.ActionSupport;
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
    private String error;
    private User user = new User();

    @Override
    public String execute() throws RemoteException, SQLException {
        AuthRepository auth = new AuthRepository();
        System.out.println(user);
        user = auth.login(user.getUsername(), user.getPassword());

        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}

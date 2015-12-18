package actions.Auth;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.User;
import org.apache.struts2.interceptor.SessionAware;
import repositories.AuthRepository;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by mariobalca on 12/16/15.
 */
public class RegisterAction extends ActionSupport implements SessionAware{
    private Map<String, Object> session;
    private String error;
    private User user = new User();

    @Override
    public String execute() throws RemoteException, SQLException {
        AuthRepository auth = new AuthRepository();
        System.out.println(user);
        user = auth.register(user.getUsername(), user.getPassword());
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

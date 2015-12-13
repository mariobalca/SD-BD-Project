package actions.Users;

import com.opensymphony.xwork2.ActionSupport;
import models.UserBean;
import org.apache.struts2.interceptor.SessionAware;
import rmi.RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by pedro on 12/8/15.
 */
public class SignUpAction extends ActionSupport implements SessionAware{
    private Map<String,Object> session;
    private String username, password;
    private String error;
    private UserBean userBean;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String get(){
        return SUCCESS;
    }

    public String post(){
        try {
            int userId = userBean.register();
            if(userId>1) {
                session.put("username", username);
                session.put("usernameId",userId);
                session.put("requestId",0);
                return "success";
            }
            else{
                setError("That username already exists");
                return "failed";
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            return "rmidown";
        } catch (SQLException e) {
            e.printStackTrace();
            return "rmidown";
        }
    }


    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}

package actions.Users;

import com.opensymphony.xwork2.ActionSupport;
import models.UserBean;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import rmi.RMI;

import javax.servlet.http.HttpServletRequest;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by pedro on 12/4/15.
 */


public class LoginAction extends ActionSupport implements SessionAware{
    private String username = null, password = null;
    private String error;
    private Map<String, Object> session;
    private UserBean userBean;

    public String get(){
        if(session.get("username")!=null)
            return "redirect";
        return SUCCESS;
    }
    public String post(){
        try {
            int login[] = getUserBean().login();
            if(login[0]>0) {
                session.put("username", userBean.getUsername());
                session.put("usernameId",login[0]);
                session.put("requestId",login[1]);
                return "success";
            }
            else{
                setError("Wrong Username/Password");
                return "failed";
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            return "failed";
        } catch (SQLException e) {
            e.printStackTrace();
            return "failed";
        }
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

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}

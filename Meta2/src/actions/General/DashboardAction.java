package actions.General;

import com.opensymphony.xwork2.ActionSupport;
import models.UserBean;
import org.apache.struts2.interceptor.SessionAware;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by mariobalca on 12/13/15.
 */
public class DashboardAction extends ActionSupport implements SessionAware {
    private Map<String, Object> session;
    private double balance;
    public String execute() throws RemoteException, SQLException {
        this.setBalance();

        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public void setBalance() throws RemoteException, SQLException {
        UserBean user = new UserBean();
        this.balance = user.getBalance((int) session.get("usernameId"));
    }

    public double getBalance() {
        return this.balance;
    }
}

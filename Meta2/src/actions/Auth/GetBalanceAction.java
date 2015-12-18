package actions.Auth;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.JsonResponse;
import genericclasses.User;
import repositories.AuthRepository;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by mariobalca on 12/17/15.
 */
public class GetBalanceAction extends ActionSupport {
    private double balance;
    private User user;
    private JsonResponse jsonResponse;
    public String execute(){
        AuthRepository authRepo = new AuthRepository();
        balance = authRepo.getBalance(user.getId());
        return SUCCESS;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JsonResponse getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(JsonResponse jsonResponse) {
        this.jsonResponse = jsonResponse;
    }
}

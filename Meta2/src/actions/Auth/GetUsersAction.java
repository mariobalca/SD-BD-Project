package actions.Auth;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.User;
import repositories.AuthRepository;

import java.util.ArrayList;

/**
 * Created by pedrocb on 18/12/2015.
 */
public class GetUsersAction extends ActionSupport{
    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public String execute(){
        System.out.printf("OLA");
        AuthRepository authRepository = new AuthRepository();
        users = authRepository.getUsers();
        return SUCCESS;
    }
}

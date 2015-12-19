package actions.Auth;

import com.opensymphony.xwork2.ActionSupport;
import repositories.AuthRepository;

/**
 * Created by pedrocb on 19/12/2015.
 */
public class SignInWithTumblrAction extends ActionSupport{
    private String auth_url;

    public String execute() {
        AuthRepository authRepository = new AuthRepository();
        setAuth_url(authRepository.registerTumblr());
        return SUCCESS;
    }

    public String getAuth_url() {
        return auth_url;
    }

    public void setAuth_url(String auth_url) {
        this.auth_url = auth_url;
    }

}

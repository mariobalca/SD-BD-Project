package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import repositories.AuthRepository;

/**
 * Created by pedrocb on 19/12/2015.
 */
public class CallbackAction extends ActionSupport{
    private String oauth_token;
    private String oauth_verifier;

    @Override
    public String execute(){
        AuthRepository authRepository = new AuthRepository();
        System.out.println(oauth_token+ " " + oauth_verifier);
        authRepository.createUserTumblr(oauth_token,oauth_verifier);
        return SUCCESS;
    }

    public String getOauth_token() {
        return oauth_token;
    }

    public void setOauth_token(String oauth_token) {
        this.oauth_token = oauth_token;
    }

    public String getOauth_verifier() {
        return oauth_verifier;
    }

    public void setOauth_verifier(String oauth_verifier) {
        this.oauth_verifier = oauth_verifier;
    }
}

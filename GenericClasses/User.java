import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mariobalca on 24-10-2015.
 */
public class User implements Serializable {
    private int id;
    private String username;
    private String password;

    public User(String u, String p, double b){
        this.username = u;
        this.password = p;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        username = username;
    }

    public String toString(){
        return this.getUsername() + " | " + this.getPassword();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

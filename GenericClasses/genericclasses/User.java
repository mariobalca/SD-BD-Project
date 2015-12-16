package genericclasses;

import java.io.Serializable;

/**
 * Created by mariobalca on 24-10-2015.
 */
public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private int requests;

    public User(String u, String p){
        this.username = u;
        this.password = p;
    }

    public User() { }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toString(){
        return this.username + " | " + this.password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRequests() {
        return requests;
    }

    public void setRequests(int requests) {
        this.requests = requests;
    }
}

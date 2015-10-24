import java.io.Serializable;

/**
 * Created by mariobalca on 24-10-2015.
 */
public class User implements Serializable {
    private String Username;
    private String Password;
    private double Balance;

    public User(String u, String p, double b){
        this.setUsername(u);
        this.setPassword(p);
        this.setBalance(b);
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String toString(){
        return "Username: " + this.getUsername() + " Password: " + this.getPassword() + " Balance: " + this.getBalance();
    }
}
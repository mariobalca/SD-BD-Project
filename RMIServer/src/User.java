import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mariobalca on 24-10-2015.
 */
public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private double balance;
    private ArrayList<Project> myProjects;
    private ArrayList<Project> myAdminProjects;
    private ArrayList<Reward> myRewards;
    private ArrayList<Extra> myExtras;

    public User(int id, String u, String p, double b){
        this.id = id;
        this.username = u;
        this.password = p;
        this.balance = b;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        balance = balance;
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
        return this.getUsername() + " | " + this.getPassword() + " | " + this.getBalance();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Project> getMyProjects() {
        return myProjects;
    }

    public void setMyProjects(ArrayList<Project> myProjects) {
        this.myProjects = myProjects;
    }

    public ArrayList<Project> getMyAdminProjects() {
        return myAdminProjects;
    }

    public void setMyAdminProjects(ArrayList<Project> myAdminProjects) {
        this.myAdminProjects = myAdminProjects;
    }

    public ArrayList<Reward> getMyRewards() {
        return myRewards;
    }

    public void setMyRewards(ArrayList<Reward> myRewards) {
        this.myRewards = myRewards;
    }

    public ArrayList<Extra> getMyExtras() {
        return myExtras;
    }

    public void setMyExtras(ArrayList<Extra> myExtras) {
        this.myExtras = myExtras;
    }
}

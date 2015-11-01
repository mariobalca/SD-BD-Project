import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mariobalca on 25-10-2015.
 */
public class Project implements Serializable {
    private int id;
    private String name;
    private Date deadline;
    private double objective;
    private String description;
    private boolean active;
    private ArrayList<Message> messages;
    private ArrayList<Extra> extras;
    private ArrayList<Reward> rewards;
    private ArrayList<Path> paths;


    public Project(String n, Date deadline, double objective, String description, boolean active){
        this.name = n;
        this.deadline = deadline;
        this.objective = objective;
        this.description = description;
        this.active = active;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public double getObjective() {
        return objective;
    }

    public void setObjective(double objective) {
        this.objective = objective;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(ArrayList<Reward> rewards) {
        this.rewards = rewards;
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }

    public void setPaths(ArrayList<Path> paths) {
        this.paths = paths;
    }

    @Override
    public String toString() {
        return "Nome: " + name + "\nDeadline: " + deadline + "\nObjective: " + objective + "\nDescription: " + description + "\nId: " + id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String detailed() {
        String s = "Project's name: " + name + "\nDeadline: " + deadline + "\nObjective: " + objective + "\nDescription: " + description + "\nId: " + id + "\n";
        if (paths.size() > 0) {
            s += "Paths:\n";
            for (Path path : paths) {
                s += path.toString() + "\n";
            }
        }
        if (rewards.size() > 0) {
            s += "Rewards:\n";
            for (Reward reward : rewards) {
                s += reward.toString() + "\n";
            }
        }
        return s;
    }
    public ArrayList<Extra> getExtras() {
        return extras;
    }

    public void setExtras(ArrayList<Extra> extras) {
        this.extras = extras;
    }

    public void setId(int id) {
        this.id = id;
    }
}

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mariobalca on 25-10-2015.
 */
public class Project implements Serializable{
    private int id;
    private String name;
    private Date deadline;
    private double objective;
    private String description;
    private ArrayList<Message> messages;
    private ArrayList<Reward> rewards;
    private ArrayList<Path> paths;

    public Project(int id, String n, Date deadline, double objective, String description){
        this.id = id;
        this.name = n;
        this.deadline = deadline;
        this.objective = objective;
        this.description = description;
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
        return "Nome: " + name + "\nDeadline: " + deadline + "\nObjective: " + objective + "\nDescription: " + description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

import java.io.Serializable;

/**
 * Created by mariobalca on 25-10-2015.
 */
public class Path implements Serializable {
    private int id;
    private String name;
    private String description;
    private int votes;

    public Path(String n, String d, int votes){
        this.name = n;
        this.description = d;
        this.votes = votes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void setId(int id) {
        this.id = id;
    }
}

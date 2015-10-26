/**
 * Created by mariobalca on 25-10-2015.
 */
public class Path {
    private int id;
    private String name;
    private String description;
    private int votes;

    public Path(int id, String n, String d){
        this.id = id;
        this.name = n;
        this.description = d;
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
}

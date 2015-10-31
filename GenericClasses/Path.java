import java.io.Serializable;

/**
 * Created by mariobalca on 25-10-2015.
 */
public class Path implements Serializable {
    private int id;
    private String name;
    private String description;
    private double value;

    public Path(String n, String d, double value){
        this.name = n;
        this.description = d;
        this.value = value;
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

    @Override
    public String toString() {
        return "Id: " + id + " Name: " + name + " Description: " + description;
    }
    public void setId(int id) {
        this.id = id;

    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

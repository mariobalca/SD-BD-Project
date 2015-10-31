import java.io.Serializable;

/**
 * Created by mariobalca on 25-10-2015.
 */
public class Reward implements Serializable {
    private int id;
    private double minValue;
    private String name;
    private String description;
    private boolean flag;

    public Reward(double min, String n, String d){
        this.minValue = min;
        this.name = n;
        this.description = d;
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

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Value: " + minValue + " Name: " + name + " Description: " + description;
    }
    public void setId(int id) {
        this.id = id;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

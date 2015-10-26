/**
 * Created by mariobalca on 25-10-2015.
 */
public class Reward {
    private int id;
    private double minValue;
    private String name;
    private String description;

    public Reward(int id, double min, String n, String d){
        this.id = id;
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
}

import java.util.ArrayList;

/**
 * Created by pedro on 27-10-2015.
 */
public class ProjectListResponse extends Response{
    public ArrayList<Project> projects;
    public ProjectListResponse(String response, ArrayList<Project> projects) {
        super(response);
        this.projects = projects;
    }

}
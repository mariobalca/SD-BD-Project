package actions.Admin;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.User;
import repositories.AdminRepository;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Created by pedrocb on 18/12/2015.
 */
public class GetAdminsAction extends ActionSupport{
    private int projectId;
    private ArrayList<User> admins;

    public ArrayList<User> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<User> admins) {
        this.admins = admins;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String execute(){
        AdminRepository adminRepository = new AdminRepository();
        admins = adminRepository.getAdmins(projectId);
        return SUCCESS;
    }
}

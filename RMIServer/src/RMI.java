/**
 * Created by mariobalca on 24-10-2015.
 */

import java.io.Serializable;
import java.rmi.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RMI extends Remote, Serializable{
    public ArrayList<User> getUsers() throws java.rmi.RemoteException, SQLException;
    /*public ArrayList<User> getAdmins(Project project) throws java.rmi.RemoteException, SQLException;
    public ArrayList<Project> getProjects() throws java.rmi.RemoteException, SQLException;
    public ArrayList<Project> getOlderProjects() throws java.rmi.RemoteException, SQLException;
    public ArrayList<Project> getUserProjects(User user) throws java.rmi.RemoteException, SQLException;
    public ArrayList<Reward> getRewards(User user) throws java.rmi.RemoteException, SQLException;
    public ArrayList<Extra> getExtraRewards(User user) throws java.rmi.RemoteException, SQLException;
    public ArrayList<Message> getMessages(Project project) throws java.rmi.RemoteException, SQLException;


    public boolean createProject() throws java.rmi.RemoteException, SQLException;
//  public boolean editProject(Project project) throws java.rmi.RemoteException, SQLException;
    public boolean removeProject(Project project) throws java.rmi.RemoteException, SQLException;
    public boolean financeProject(Project project, User user) throws java.rmi.RemoteException, SQLException;


    public double getBalance(User user) throws java.rmi.RemoteException, SQLException;


    public boolean sendMessage(String message) throws java.rmi.RemoteException, SQLException;
    public boolean answerMessage(Message message) throws java.rmi.RemoteException, SQLException;


    public boolean addReward() throws java.rmi.RemoteException, SQLException;
    public boolean removeReward(Reward reward) throws java.rmi.RemoteException, SQLException;
    public boolean winReward(Reward reward, User user) throws java.rmi.RemoteException, SQLException;
    public boolean giveReward(Reward reward, User giver, User receiver) throws java.rmi.RemoteException, SQLException;


    public boolean createPath() throws java.rmi.RemoteException, SQLException;
    public boolean deletePath() throws java.rmi.RemoteException, SQLException;
    public boolean votePath(Path path) throws java.rmi.RemoteException, SQLException;


    public boolean addExtraReward() throws java.rmi.RemoteException, SQLException;
    public boolean removeExtraReward(Extra extra) throws java.rmi.RemoteException, SQLException;
    public boolean winExtraReward(Extra extra, User user) throws java.rmi.RemoteException, SQLException;
*/
}
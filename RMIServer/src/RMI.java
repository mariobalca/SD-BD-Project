/**
 * Created by mariobalca on 24-10-2015.
 */

import java.io.Serializable;
import java.rmi.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RMI extends Remote, Serializable{

    // IDEMPOTENTES
    ArrayList<User> getUsers() throws java.rmi.RemoteException, SQLException;
    ArrayList<User> getAdmins(int projectId) throws java.rmi.RemoteException, SQLException;
    ArrayList<Project> getProjects() throws java.rmi.RemoteException, SQLException;
    ArrayList<Project> getOlderProjects() throws java.rmi.RemoteException, SQLException;
    ArrayList<Project> getUserProjects(int projectId) throws java.rmi.RemoteException, SQLException;
    ArrayList<Reward> getRewards(int userId) throws java.rmi.RemoteException, SQLException;
    ArrayList<Extra> getExtraRewards(int userId) throws java.rmi.RemoteException, SQLException;
    ArrayList<Message> getMessages(int projectId) throws java.rmi.RemoteException, SQLException;
    public double getBalance(int userId) throws java.rmi.RemoteException, SQLException;
    public int[] loginUser(String username, String password) throws java.rmi.RemoteException, SQLException;

    // NÃO IDEMPOTENTES
    public int registerUser(String username, String password) throws java.rmi.RemoteException, SQLException;
    public boolean createProject(Project project, int requestId, int userId) throws java.rmi.RemoteException, SQLException;
    public boolean removeProject(int projectId, int requestId, int userId) throws java.rmi.RemoteException, SQLException;
    public boolean financeProject(int projectId, int requestId, int userId, int pathId, double value) throws java.rmi.RemoteException, SQLException;


    public boolean sendMessage(Message message, int projectId, int requestId) throws java.rmi.RemoteException, SQLException;
    public boolean answerMessage(int messageId, String response, int requestId, int userId) throws java.rmi.RemoteException, SQLException;


    public boolean createPath(Path path, int requestId, int userId, int projectId) throws java.rmi.RemoteException, SQLException;
    public boolean deletePath(int pathId, int requestId, int userId) throws java.rmi.RemoteException, SQLException;


    public boolean addReward(Reward reward, int requestId, int projectId, int userId) throws java.rmi.RemoteException, SQLException;
    public boolean removeReward(int rewardId, int requestId, int userId) throws java.rmi.RemoteException, SQLException;
    public boolean winReward(int rewardId, int requestId, int userId) throws java.rmi.RemoteException, SQLException;
    public boolean giveReward(int rewardId, int requestId, int giverUserId, int receiverUserId) throws java.rmi.RemoteException, SQLException;


    public boolean addExtraReward(Extra extra, int requestId, int projectId, int userId) throws java.rmi.RemoteException, SQLException;
    public boolean removeExtraReward(int extraId, int requestId, int userId) throws java.rmi.RemoteException, SQLException;
    public boolean winExtraReward(int  extraId, int requestId, int userId) throws java.rmi.RemoteException, SQLException;

}
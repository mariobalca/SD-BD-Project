/**
 * Created by mariobalca on 24-10-2015.
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class RMIImpl extends UnicastRemoteObject implements RMI  {
    static Connection connection;
    static Statement statement;
    protected RMIImpl(Connection connection) throws RemoteException, SQLException {
        super();
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    public ArrayList<User> getUsers() throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("Select * from Users");
        ArrayList<User> users = new ArrayList<User>();
        while(result.next())
        {
            users.add(new User(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4)));
        }
        System.out.println("Get Users executed");
        return users;
    }

    public ArrayList<User> getAdmins(int projectId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Administrators where projectId = " + projectId);
        ArrayList<User> users = new ArrayList<User>();
        while(result.next())
        {
            users.add(new User(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4)));
        }
        System.out.println("Get Admins executed");
        return users;
    }

    public ArrayList<Project> getProjects() throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Projects");
        ArrayList<Project> projects = new ArrayList<Project>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while(result.next())
        {
            try {
                Date projectDate = format.parse(result.getString(3));
                if(new Date().before(projectDate))
                    projects.add(new Project(result.getInt(1), result.getString(2),  format.parse(result.getString(3)), result.getDouble(4), result.getString(5)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Get Projects executed");
        return projects;
    }

    public ArrayList<Project> getOlderProjects() throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Projects");
        ArrayList<Project> projects = new ArrayList<Project>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while(result.next())
        {
            try {
                Date projectDate = format.parse(result.getString(3));
                System.out.println(new Date().toString());
                if(new Date().after(projectDate))
                    projects.add(new Project(result.getInt(1), result.getString(2), projectDate  , result.getDouble(4), result.getString(5)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Get Older Projects executed");
        return projects;
    }

    public ArrayList<Project> getUserProjects(int userId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Projects where OwnerUserId = " + userId);
        ArrayList<Project> projects = new ArrayList<Project>();;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while(result.next())
        {
            try {
                projects.add(new Project(result.getInt(1), result.getString(2),  format.parse(result.getString(3)), result.getDouble(4), result.getString(5)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Get User Projects executed");
        return projects;
    }

    public ArrayList<Reward> getRewards(int userId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Rewards_Users where OwnerUserId = " + userId);
        ArrayList<Reward> rewards = new ArrayList<Reward>();
        while(result.next()){
            rewards.add(new Reward(result.getInt(1), result.getDouble(2), result.getString(3), result.getString(4)));
        }
        System.out.println("Get Rewards executed");
        return rewards;
    }

    public ArrayList<Extra> getExtraRewards(int userId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Extras_Users where OwnerUserId = " + userId);
        ArrayList<Extra> extraRewards = new ArrayList<Extra>();
        while(result.next())
        {
            extraRewards.add(new Extra(result.getInt(1), result.getDouble(2), result.getString(3), result.getString(4)));
        }
        System.out.println("Get Extras executed");
        return extraRewards;
    }

    public ArrayList<Message> getMessages(int projectId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("select * from messages");
        ArrayList<Message> messages = new ArrayList<Message>();
        while(result.next())
        {
            ResultSet fromUserResult = statement.executeQuery("select * from Users where fromUserId = " + result.getInt(5));
            User fromUser = new User(fromUserResult.getInt(1), fromUserResult.getString(2), fromUserResult.getString(3), fromUserResult.getDouble(4));
            ResultSet toUserResult = statement.executeQuery("select * from Users where toUserId = " + result.getInt(5));
            User toUser = new User(toUserResult.getInt(1), toUserResult.getString(2), toUserResult.getString(3), toUserResult.getDouble(4));

            messages.add(new Message(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), fromUser, toUser));
        }
        System.out.println("Get Messages executed");
        return messages;
    }

    public double getBalance(int userId) throws java.rmi.RemoteException, SQLException {
        ResultSet result = statement.executeQuery("select Balance from Users where id = " + userId);
        double balance = result.getDouble(1);

        System.out.println("Get Balance executed");
        return balance;
    }

    public boolean createProject(Project project, int userId) throws RemoteException, SQLException {
        return false;
    }

    public boolean removeProject(int projectId) throws RemoteException, SQLException {
        return false;
    }

    public boolean financeProject(int projectId, int userId, int pathId) throws RemoteException, SQLException {
        return false;
    }

    public boolean sendMessage(Message message) throws RemoteException, SQLException {
        return false;
    }

    public boolean answerMessage(int messageId) throws RemoteException, SQLException {
        return false;
    }

    public boolean addReward(Reward reward) throws RemoteException, SQLException {
        return false;
    }

    public boolean removeReward(int rewardId) throws RemoteException, SQLException {
        return false;
    }

    public boolean winReward(int rewardId, int userId) throws RemoteException, SQLException {
        return false;
    }

    public boolean giveReward(int rewardId, int giverUserId, int receiverUserId) throws RemoteException, SQLException {
        return false;
    }

    public boolean createPath(Path path, int projectId) throws RemoteException, SQLException {
        return false;
    }

    public boolean deletePath(int pathId) throws RemoteException, SQLException {
        return false;
    }

    public boolean addExtraReward(Extra extra) throws RemoteException, SQLException {
        return false;
    }

    public boolean removeExtraReward(int extraId) throws RemoteException, SQLException {
        return false;
    }

    public boolean winExtraReward(int extraId, int userId) throws RemoteException, SQLException {
        return false;
    }
}

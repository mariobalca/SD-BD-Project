/**
 * Created by mariobalca on 24-10-2015.
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;

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
        String returnValue = "";
        ArrayList<User> users = new ArrayList<User>();
        while(result.next())
        {
            users.add(new User(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4)));
        }
        System.out.println("Get Users executed");
        return users;
    }

    public ArrayList<User> getAdmins(Project project) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Users");
        String returnValue = "";
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
        String returnValue = "";
        ArrayList<Project> projects = new ArrayList<Project>();
        while(result.next())
        {
            projects.add(new Project(result.getInt(1), result.getString(2), result.getDate(3), result.getDouble(4), result.getString(5)));
        }
        System.out.println("Get Projects executed");
        return projects;
    }

    public ArrayList<Project> getOlderProjects() throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Projects");
        String returnValue = "";
        ArrayList<Project> projects = new ArrayList<Project>();
        while(result.next())
        {
            projects.add(new Project(result.getInt(1), result.getString(2), result.getDate(3), result.getDouble(4), result.getString(5)));
        }
        System.out.println("Get Older Projects executed");
        return projects;
    }

    public ArrayList<Project> getUserProjects(User user) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Projects where OwnerId = " + user.getId());
        String returnValue = "";
        ArrayList<Project> projects = new ArrayList<Project>();
        while(result.next())
        {
            projects.add(new Project(result.getInt(1), result.getString(2), result.getDate(3), result.getDouble(4), result.getString(5)));
        }
        System.out.println("Get users executed");
        return projects;
    }

    public ArrayList<Reward> getRewards(User user) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Rewards_Users where OwnerId = " + user.getId());
        String returnValue = "";
        ArrayList<Reward> rewards = new ArrayList<Reward>();
        while(result.next())
        {
            //rewards.add(new Reward());
        }
        System.out.println("Get Rewards executed");
        return rewards;
    }

    public ArrayList<Extra> getExtraRewards(User user) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Extras_Users where OwnerId = " + user.getId());
        String returnValue = "";
        ArrayList<Extra> extraRewards = new ArrayList<Extra>();
        while(result.next())
        {
            //extraRewards.add(new Extra());
        }
        System.out.println("Get Extras executed");
        return extraRewards;
    }

    public ArrayList<Message> getMessages(Project project) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Messages where ProjectId = " + project.getId());
        String returnValue = "";
        ArrayList<Message> messages = new ArrayList<Message>();
        while(result.next())
        {
            //messages.add(new Message());
        }
        System.out.println("Get Messages executed");
        return messages;
    }
}

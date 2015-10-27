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
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while(result.next())
        {
            try {
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
        String returnValue = "";
        ArrayList<Project> projects = new ArrayList<Project>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while(result.next())
        {
            try {
                projects.add(new Project(result.getInt(1), result.getString(2),  format.parse(result.getString(3)), result.getDouble(4), result.getString(5)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Get Older Projects executed");
        return projects;
    }

    public ArrayList<Project> getUserProjects(User user) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Projects where OwnerUserId = " + user.getId());
        String returnValue = "";
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

    public ArrayList<Reward> getRewards(User user) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Rewards_Users where OwnerUserId = " + user.getId());
        String returnValue = "";
        ArrayList<Reward> rewards = new ArrayList<Reward>();
        while(result.next())
        {
<<<<<<< HEAD
            //rewards.add(new Reward());
=======
            rewards.add(new Reward(result.getInt(1), result.getDouble(2), result.getString(3), result.getString(4)));
>>>>>>> 20e5d4792065df41c0d647e0ce4bd68a13a84488
        }
        System.out.println("Get Rewards executed");
        return rewards;
    }

    public ArrayList<Extra> getExtraRewards(User user) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Extras_Users where OwnerUserId = " + user.getId());
        String returnValue = "";
        ArrayList<Extra> extraRewards = new ArrayList<Extra>();
        while(result.next())
        {
<<<<<<< HEAD
            //extraRewards.add(new Extra());
=======
            extraRewards.add(new Extra(result.getInt(1), result.getDouble(2), result.getString(3), result.getString(4)));
>>>>>>> 20e5d4792065df41c0d647e0ce4bd68a13a84488
        }
        System.out.println("Get Extras executed");
        return extraRewards;
    }

    public ArrayList<Message> getMessages(Project project) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select Messages.id, Messages.Subject, Messages.Question, Messages.Response, Users.Username, Users.Username from Messages INNER JOIN Users ON Messages.ToUserId = Users.id AND Messages.FromUserId = Users.id where ProjectId = " + project.getId());
        String returnValue = "";
        ArrayList<Message> messages = new ArrayList<Message>();
        while(result.next())
        {
<<<<<<< HEAD
            //messages.add(new Message());
=======
            messages.add(new Message(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6)));
>>>>>>> 20e5d4792065df41c0d647e0ce4bd68a13a84488
        }
        System.out.println("Get Messages executed");
        return messages;
    }
}

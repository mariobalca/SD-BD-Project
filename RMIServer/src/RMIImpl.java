/**
 * Created by mariobalca on 24-10-2015.
 */

import javax.lang.model.type.ArrayType;
import javax.xml.transform.Result;
import java.nio.file.Paths;
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
        this.statement.execute("PRAGMA foreign_keys = ON");
    }

    // IDEMPOTENTES

    public ArrayList<User> getUsers() throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("Select * from Users");
        ArrayList<User> users = new ArrayList<User>();
        while(result.next())
        {
            User u = new User(result.getString(2), result.getString(3), result.getDouble(4));
            u.setId(result.getInt(1));
            users.add(u);
        }
        System.out.println("Get Users executed");
        return users;
    }

    public ArrayList<User> getAdmins(int projectId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Administrators where projectId = " + projectId);
        ArrayList<User> users = new ArrayList<User>();
        while(result.next())
        {
            User u = new User(result.getString(2), result.getString(3), result.getDouble(4));
            u.setId(result.getInt(1));
            users.add(u);
        }
        System.out.println("Get Admins executed");
        return users;
    }

    public ArrayList<Project> getProjectsWithoutDetails() throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Projects where active = 1");
        ArrayList<Project> projects = new ArrayList<Project>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while(result.next())
        {
            try {
                Date projectDate = format.parse(result.getString(3));
                Project p = new Project(result.getString(2), projectDate, result.getDouble(4), result.getString(5), result.getBoolean(6));
                int projectId = result.getInt(1);
                p.setId(projectId);
                projects.add(p);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Get Projects executed");
        return projects;
    }

    public ArrayList<Project> getProjects() throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Projects where active = 1");
        ArrayList<Project> projects = new ArrayList<Project>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while(result.next())
        {
            try {
                Date projectDate = format.parse(result.getString(3));
                Project p = new Project(result.getString(2), projectDate, result.getDouble(4), result.getString(5), result.getBoolean(6));
                int projectId = result.getInt(1);
                p.setId(projectId);
                p.setPaths(this.getProjectPaths(projectId));
                p.setMessages(this.getProjectMessages(projectId));
                p.setRewards(this.getProjectRewards(projectId));
                p.setExtras(this.getProjectExtraRewards(projectId));
                projects.add(p);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Get Projects executed");
        return projects;
    }

    public ArrayList<Project> getOlderProjects() throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Projects where active = 0");
        ArrayList<Project> projects = new ArrayList<Project>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while(result.next())
        {
            try {
                Date projectDate = format.parse(result.getString(3));
                Project p = new Project(result.getString(2), projectDate, result.getDouble(4), result.getString(5), result.getBoolean(6));
                int projectId = result.getInt(1);
                p.setId(projectId);
                p.setPaths(this.getProjectPaths(projectId));
                p.setMessages(this.getProjectMessages(projectId));
                p.setRewards(this.getProjectRewards(projectId));
                p.setExtras(this.getProjectExtraRewards(projectId));
                projects.add(p);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Get Older Projects executed");
        return projects;
    }

    public ArrayList<Project> getOwnedProjects(int userId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Projects where OwnerUserId = " + userId);
        ArrayList<Project> projects = new ArrayList<Project>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while(result.next())
        {
            try {
                Date projectDate = format.parse(result.getString(3));
                Project p = new Project(result.getString(2), projectDate, result.getDouble(4), result.getString(5), result.getBoolean(6));
                int projectId = result.getInt(1);
                p.setId(projectId);
                p.setPaths(this.getProjectPaths(projectId));
                p.setMessages(this.getProjectMessages(projectId));
                p.setRewards(this.getProjectRewards(projectId));
                p.setExtras(this.getProjectExtraRewards(projectId));
                projects.add(p);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Get Owned Projects executed");
        return projects;
    }

    public ArrayList<Project> getAdminProjects(int userId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select Projects.* from Projects, Administrators where UserId = " + userId + " and Projects.id = ProjectId and active = 1");
        ArrayList<Project> projects = new ArrayList<Project>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while(result.next())
        {
            try {
                Date projectDate = format.parse(result.getString(3));
                Project p = new Project(result.getString(2), projectDate, result.getDouble(4), result.getString(5), result.getBoolean(6));
                int projectId = result.getInt(1);
                p.setId(projectId);
                p.setPaths(this.getProjectPaths(projectId));
                p.setMessages(this.getProjectMessages(projectId));
                p.setRewards(this.getProjectRewards(projectId));
                p.setExtras(this.getProjectExtraRewards(projectId));
                projects.add(p);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Get Administrated Projects executed");
        return projects;
    }

    public ArrayList<Reward> getProjectRewards(int projectId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Rewards_Users, Rewards where ProjectId = " + projectId + " and RewardId = Rewards.id ");
        ArrayList<Reward> rewards = new ArrayList<Reward>();

        while(result.next()){
            Reward r = new Reward(result.getDouble(2), result.getString(3), result.getString(4));
            r.setId(result.getInt(1));
            rewards.add(r);
        }
        System.out.println("Get User Rewards executed");
        return rewards;
    }

    public ArrayList<Extra> getProjectExtraRewards(int projectId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Extras_Users, Extras where ProjectId = " + projectId + " and ExtraId = Extras.id ");
        ArrayList<Extra> extraRewards = new ArrayList<Extra>();
        while(result.next())
        {
            Extra e = new Extra(result.getDouble(2), result.getString(3), result.getString(4));
            e.setId(result.getInt(1));
            extraRewards.add(e);
        }
        System.out.println("Get User Extras executed");
        return extraRewards;
    }

    public ArrayList<Reward> getUserRewards(int userId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Rewards_Users where OwnerUserId = " + userId);
        ArrayList<Reward> rewards = new ArrayList<Reward>();

        while(result.next()){
            Reward r = new Reward(result.getDouble(2), result.getString(3), result.getString(4));
            r.setId(result.getInt(1));
            rewards.add(r);
        }
        System.out.println("Get User Rewards executed");
        return rewards;
    }

    public ArrayList<Extra> getUserExtraRewards(int userId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("Select * from Extras_Users where OwnerUserId = " + userId);
        ArrayList<Extra> extraRewards = new ArrayList<Extra>();
        while(result.next())
        {
            Extra e = new Extra(result.getDouble(2), result.getString(3), result.getString(4));
            e.setId(result.getInt(1));
            extraRewards.add(e);
        }
        System.out.println("Get User Extras executed");
        return extraRewards;
    }

    public ArrayList<Path> getProjectPaths(int projectId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("select * from paths where projectId = " + projectId);
        ArrayList<Path> paths = new ArrayList<Path>();
        while(result.next())
        {
            int votes = statement.executeQuery("select count(*) from Votes where pathId = " + result.getInt(1)).getInt(1);

            Path p = new Path(result.getString(2), result.getString(4), votes);
            p.setId(result.getInt(1));
            p.setVotes(votes);
            paths.add(p);
        }
        System.out.println("Get Paths executed");
        return paths;
    }

    public ArrayList<Message> getProjectMessages(int projectId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = statement.executeQuery("select * from messages where projectId = " + projectId);
        ArrayList<Message> messages = new ArrayList<Message>();
        while(result.next())
        {
            ResultSet fromUserResult = statement.executeQuery("select * from Users where fromUserId = " + result.getInt(5));
            User fromUser = new User(fromUserResult.getString(2), fromUserResult.getString(3), fromUserResult.getDouble(4));
            fromUser.setId(fromUserResult.getInt(1));

            Message m = new Message(result.getString(2), result.getString(3), result.getString(4), fromUser);
            m.setId(result.getInt(1));
            messages.add(m);
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

    public int[] loginUser(String username, String password) throws SQLException {
        ResultSet result = statement.executeQuery("select count(*), id from Users where username = \"" + username + "\" and password = \"" + password + "\"");
        if(result.getInt(1) == 1){
            int userId = result.getInt(2);
            result = statement.executeQuery("select count(*) from logs where userId = " + userId);
            return new int[]{userId, result.getInt(1)};
        }
        else{
            return new int[]{0, 0};
        }
    }


    // NÃO IDEMPOTENTES


    public int registerUser(String username, String password) throws SQLException {
        ResultSet result = statement.executeQuery("select count(*) from Users where username = \"" + username +"\"");
        if (result.getInt(1) == 0){
            statement.execute("insert into users (username, password, balance) values (\"" + username + "\", \"" + password + "\", " + 100 + ")");
            result = statement.executeQuery("select id from Users where username = \"" + username +"\"");
            return result.getInt(1);
        }
        else {
            return 0;
        }
    }

    public boolean createProject(Project project, int requestId, int userId) throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            statement.execute("insert into projects (Name, Deadline, Objective, Description, OwnerUserId,Active) values (\"" + project.getName() + "\", \"" + dateFormat.format(project.getDeadline()) + "\"," + project.getObjective() + ", \"" + project.getDescription() + "\", " + userId + "," +1+ ")");
            System.out.println("select id from projects where Name = \"" + project.getName()+"\"");
            result = statement.executeQuery("select id from projects where Name = \"" + project.getName()+"\"");
            int projectId = result.getInt(1);
            statement.execute("insert into administrators (ProjectId, UserId) values (" + projectId + ", " + userId + ")");
            statement.execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");
            System.out.println(projectId);
            for(Reward reward :project.getRewards()){
                this.addReward(reward, 0, projectId, userId);
            }
            for(Path path:project.getPaths()){
                this.createPath(path, 0, userId, projectId);
            }
            return true;
        }
        else{
            result = statement.executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean cancelProject(int projectId, int requestId, int userId) throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            result = statement.executeQuery("select count(*) from administrators where userId = " + userId + " and projectId= " + projectId);
            if(result.getInt(1) == 0)
                return false;

            result = statement.executeQuery("select UserId, Value from transactions where ProjectId = " + projectId);
            while(result.next())
            {
                double balance = statement.executeQuery("select balance from users where id = " + result.getInt(1)).getDouble(1);
                statement.execute("update users set balance = " + (balance + result.getDouble(2)) + " where id = " + result.getInt(1));
            }

            statement.execute("update projects set active = 0 where id = " + projectId);
            statement.execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = statement.executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean endProject(int projectId) throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("select objective, OwnerUserId from projects where id = " + projectId);
        double objective = result.getDouble(1);
        int ownerUserId = result.getInt(2);
        double sum = statement.executeQuery("select sum(value) from transactions where projectId = " + projectId).getDouble(1);
        if(sum<objective) {
            result = statement.executeQuery("select UserId, Value from transactions where ProjectId = " + projectId);
            while(result.next())
            {
                double balance = statement.executeQuery("select balance from users where id = " + result.getInt(1)).getDouble(1);
                statement.execute("update users set balance = " + (balance + result.getDouble(2)) + " where id = " + result.getInt(1));
            }

            statement.execute("update projects set active = 0 where id = " + projectId);
        }
        else {
            double balance = statement.executeQuery("select balance from users where id = " + ownerUserId).getDouble(1);
            statement.execute("update users set balance = " + (sum + balance) + " where id = " + ownerUserId);
            result = statement.executeQuery("select UserId, Value from transactions where ProjectId = " + projectId);
            while(result.next())
            {
                ResultSet rewards = statement.executeQuery("select id from rewards where minValue = " + result.getDouble(2) + " and ProjectId = " + projectId);
                if(rewards.next()){
                    this.winReward(rewards.getInt(1), 0, result.getInt(1));
                }

                ResultSet extras = statement.executeQuery("select id from extras where minValue = " + sum + " and ProjectId = " + projectId);
                if(rewards.next()){
                    this.winExtraReward(extras.getInt(1), 0, result.getInt(1));
                }
            }
        }
        return true;
    }

    public boolean financeProject(int projectId, int requestId, int userId, int pathId, double value) throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            result = statement.executeQuery("select balance from users where userId = " + userId);
            if(result.getDouble(1) < value)
                return false;
            statement.execute("update users set balance = " + (result.getDouble(1) - value) + " where id = " + userId);
            statement.execute("insert into transactions (UserId, ProjectId, Value) values (" + userId + ", " + projectId + ","  + ", " + value + ")");
            result = statement.executeQuery("select id from transactions where userId = " + userId);
            result.last();
            statement.execute("insert into votes (TransactionId, PathId) values (" + result.getInt(1) + ", " + pathId + ")");
            statement.execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = statement.executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean addAdmin(int projectId, int requestId, int userId, int newAdminId) throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            result = statement.executeQuery("select * from administrators where userId = " + userId + " and projectId= " + projectId);

            if(!result.next())
                return false;

            result = statement.executeQuery("select * from administrators where userId = " + newAdminId + " and projectId= " + projectId);
            if(result.next())
                return false;

            statement.execute("insert into administrators (UserId, ProjectId) = " + newAdminId + " and projectId = " + projectId);
            statement.execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = statement.executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean removeAdmin(int projectId, int requestId, int userId, int removeAdminId) throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            result = statement.executeQuery("select administrators.*, projects.OwnerUserId from administrators, projects where userId = " + userId + " and projectId= " + projectId + " and projects.OwnerUserId != " + removeAdminId);

            if(!result.next())
                return false;

            statement.execute("delete from administrators where userId = " + removeAdminId + " and projectId = " + projectId);
            statement.execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = statement.executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean sendMessage(Message message, int projectId, int requestId) throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + message.getSender().getId());
        if(result.getInt(1) == 0){
            statement.execute("insert into messages (ProjectId, Subject, Question, Response, FromUserId) values (" + projectId + ",\"" + message.getSubject() + "\",\"" + message.getQuestion() + "\", \"\", " + message.getSender().getId() + ")");
            statement.execute("insert into logs (UserId, RequestId, Response) values (" + message.getSender().getId() + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = statement.executeQuery("select response from logs where requestId = " + requestId + " and userId = " + message.getSender().getId());
            return result.getBoolean(1);
        }
    }

    public boolean answerMessage(int messageId, String response, int requestId, int userId) throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            ResultSet projectId = statement.executeQuery("select projectId from messages where id = " + messageId + ")");
            result = statement.executeQuery("select * from administrators where userId = " + userId + " and projectId= " + projectId.getInt(1));

            if(!result.next())
                return false;

            statement.execute("update messages set response = " + response + " where id = " + messageId);
            statement.execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = statement.executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean createPath(Path path, int requestId, int userId, int projectId) throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0 || requestId == 0){
            result = statement.executeQuery("select * from administrators where userId = " + userId + " and projectId= " + projectId);

            if(!result.next())
                return false;

            statement.execute("insert into paths (Name, Description, ProjectId) values (\"" + path.getName() + "\", \"" + path.getDescription() + "\", " + projectId + ")");
            statement.execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = statement.executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean deletePath(int pathId, int requestId, int userId) throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            ResultSet projectId = statement.executeQuery("select projectId from paths where id = " + pathId + ")");
            result = statement.executeQuery("select * from administrators where userId = " + userId + " and projectId= " + projectId.getInt(1));

            if(!result.next())
                return false;

            statement.execute("delete from paths where id = " + pathId);
            statement.execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = statement.executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean addReward(Reward reward, int requestId, int projectId, int userId) throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0 || requestId == 0){
            result = statement.executeQuery("select * from administrators where userId = " + userId + " and projectId= " + projectId);

            if(!result.next())
                return false;

            statement.execute("insert into rewards (MinValue, Name, Description, ProjectId) values (" + reward.getMinValue()  + "\"" + reward.getName() + "\", \"" + reward.getDescription() + "\", " + projectId + ")");
            statement.execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = statement.executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean removeReward(int rewardId, int requestId, int userId) throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            ResultSet projectId = statement.executeQuery("select projectId from rewards where id = " + rewardId + ")");
            result = statement.executeQuery("select * from administrators where userId = " + userId + " and projectId= " + projectId.getInt(1));

            if(!result.next())
                return false;

            statement.execute("delete from rewards where id = " + rewardId);
            statement.execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = statement.executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean winReward(int rewardId, int requestId, int userId) throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0 || requestId == 0){
            statement.execute("insert into rewards_users (RewardId, WinnerUserId, OwnerUserId) values (" + rewardId + ", " + userId + ", " + userId + ")");
            statement.execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = statement.executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean giveReward(int rewardId, int requestId, int userId, int receiverUserId) throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0 || requestId == 0){
            statement.execute("update rewards_users set OwnerUserId = " + receiverUserId + " where RewardId = " + rewardId);
            statement.execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = statement.executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean addExtraReward(Extra extra, int requestId, int projectId, int userId) throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0 || requestId == 0){
            result = statement.executeQuery("select * from administrators where userId = " + userId + " and projectId= " + projectId);

            if(!result.next())
                return false;

            statement.execute("insert into extras (MinValue, Name, Description, ProjectId) values (" + extra.getMinValue()  + "\"" + extra.getName() + "\", \"" + extra.getDescription() + "\", " + projectId + ")");
            statement.execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = statement.executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean removeExtraReward(int extraId, int requestId, int userId) throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            ResultSet projectId = statement.executeQuery("select projectId from rewards where id = " + extraId + ")");
            result = statement.executeQuery("select * from administrators where userId = " + userId + " and projectId= " + projectId.getInt(1));

            if(!result.next())
                return false;

            statement.execute("delete from extras where id = " + extraId);
            statement.execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = statement.executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean winExtraReward(int extraId, int requestId, int userId) throws RemoteException, SQLException {
        ResultSet result = statement.executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            statement.execute("insert into rewards_users (RewardId, UserId) values (" + extraId + ", " + userId  + ")");
            statement.execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = statement.executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }
}

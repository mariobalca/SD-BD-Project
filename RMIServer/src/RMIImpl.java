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
    protected RMIImpl(Connection connection) throws RemoteException, SQLException {
        super();
        this.connection = connection;
        connection.createStatement().execute("PRAGMA foreign_keys = ON");
    }

    // IDEMPOTENTES

    public ArrayList<User> getUsers() throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("Select * from Users");
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
        ResultSet result = connection.createStatement().executeQuery("Select * from Administrators where projectId = " + projectId);
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
        ResultSet result = connection.createStatement().executeQuery("Select * from Projects where active = 1");
        ArrayList<Project> projects = new ArrayList<Project>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
        ResultSet result = connection.createStatement().executeQuery("Select * from Projects where active = 1");
        ArrayList<Project> projects = new ArrayList<Project>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
                p.setExtras(this.getProjectExtraLevels(projectId));
                projects.add(p);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Get Projects executed");
        return projects;
    }

    public ArrayList<Project> getOlderProjects() throws java.rmi.RemoteException, SQLException{
        ResultSet result = connection.createStatement().executeQuery("Select * from Projects where active = 0");
        ArrayList<Project> projects = new ArrayList<Project>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
                p.setExtras(this.getProjectExtraLevels(projectId));
                projects.add(p);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Get Older Projects executed");
        return projects;
    }

    public ArrayList<Project> getOwnedProjects(int userId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = connection.createStatement().executeQuery("Select * from Projects where OwnerUserId = " + userId);
        ArrayList<Project> projects = new ArrayList<Project>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
                p.setExtras(this.getProjectExtraLevels(projectId));
                projects.add(p);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Get Owned Projects executed");
        return projects;
    }

    public ArrayList<Project> getAdminProjects(int userId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = connection.createStatement().executeQuery("Select Projects.* from Projects, Administrators where UserId = " + userId + " and Projects.id = ProjectId and active = 1");
        ArrayList<Project> projects = new ArrayList<Project>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
                p.setExtras(this.getProjectExtraLevels(projectId));
                projects.add(p);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Get Administrated Projects executed");
        return projects;
    }

    public ArrayList<Reward> getProjectRewards(int projectId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = connection.createStatement().executeQuery("Select * from Rewards where ProjectId = " + projectId);
        ArrayList<Reward> rewards = new ArrayList<Reward>();

        while(result.next()){
            ResultSet rewardRS = connection.createStatement().executeQuery("select * from rewards where id = " + result.getInt(1));
            Reward r = new Reward(rewardRS.getDouble(2), rewardRS.getString(3), rewardRS.getString(4));
            r.setFlag(rewardRS.getBoolean(5));
            r.setId(rewardRS.getInt(1));
            rewards.add(r);
        }
        System.out.println("Get User Rewards executed");
        return rewards;
    }

    public ArrayList<Extra> getProjectExtraLevels(int projectId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = connection.createStatement().executeQuery("select * from extras where ProjectId = " + projectId);
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
        ResultSet result = connection.createStatement().executeQuery("Select rewardId from Rewards_Users where OwnerUserId = " + userId);
        ArrayList<Reward> rewards = new ArrayList<Reward>();

        while(result.next()){
            ResultSet rewardRS = connection.createStatement().executeQuery("select * from rewards where id = " + result.getInt(1));
            Reward r = new Reward(rewardRS.getDouble(2), rewardRS.getString(3), rewardRS.getString(4));
            r.setFlag(rewardRS.getBoolean(5));
            r.setId(rewardRS.getInt(1));
            rewards.add(r);
        }
        System.out.println("Get User Rewards executed");
        return rewards;
    }

    public ArrayList<Path> getProjectPaths(int projectId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = connection.createStatement().executeQuery("select * from paths where projectId = " + projectId);
        ArrayList<Path> paths = new ArrayList<Path>();
        int i = 0;
        while(result.next())
        {
            System.out.println(i++);
            int pathId = result.getInt(1);
            double value = connection.createStatement().executeQuery("select sum(value) from Transactions where pathId = " + pathId).getDouble(1);

            Path p = new Path(result.getString(2), result.getString(4), value);

            p.setId(pathId);
            paths.add(p);
        }
        System.out.println("Get Paths executed");
        return paths;
    }

    public ArrayList<Message> getProjectMessages(int projectId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = connection.createStatement().executeQuery("select * from messages where projectId = " + projectId);
        ArrayList<Message> messages = new ArrayList<Message>();
        while(result.next())
        {
            String subject = result.getString(3);
            String question = result.getString(4);
            String response = result.getString(5);
            ResultSet fromUserResult = connection.createStatement().executeQuery("select * from Users where fromUserId = " + result.getInt(5));
            User fromUser = new User(fromUserResult.getString(2), fromUserResult.getString(3), fromUserResult.getDouble(4));
            fromUser.setId(fromUserResult.getInt(1));

            Message m = new Message(subject, question, response, fromUser);
            m.setId(result.getInt(1));
            messages.add(m);
        }
        System.out.println("Get Messages executed");
        return messages;
    }

    public double getBalance(int userId) throws java.rmi.RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select Balance from Users where id = " + userId);
        double balance = result.getDouble(1);

        System.out.println("Get Balance executed");
        return balance;
    }

    public int[] loginUser(String username, String password) throws SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*), id from Users where username = \"" + username + "\" and password = \"" + password + "\"");
        if(result.getInt(1) == 1){
            int userId = result.getInt(2);
            result = connection.createStatement().executeQuery("select count(*) from logs where userId = " + userId);
            return new int[]{userId, result.getInt(1)};
        }
        else{
            return new int[]{0, 0};
        }
    }


    // NÃO IDEMPOTENTES


    public int registerUser(String username, String password) throws SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from Users where username = \"" + username +"\"");
        if (result.getInt(1) == 0){
            connection.createStatement().execute("insert into users (username, password, balance) values (\"" + username + "\", \"" + password + "\", " + 100 + ")");
            result = connection.createStatement().executeQuery("select id from Users where username = \"" + username +"\"");
            return result.getInt(1);
        }
        else {
            return 0;
        }
    }

    public boolean createProject(Project project, int requestId, int userId) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            result = connection.createStatement().executeQuery("select id from projects where Name = \"" + project.getName()+"\"");
            if(result.next())
                return false;

            connection.createStatement().execute("insert into projects (Name, Deadline, Objective, Description, OwnerUserId,Active) values (\"" + project.getName() + "\", \"" + dateFormat.format(project.getDeadline()) + "\"," + project.getObjective() + ", \"" + project.getDescription() + "\", " + userId + "," +1+ ")");

            result = connection.createStatement().executeQuery("select id from projects where Name = \"" + project.getName()+"\"");
            int projectId = result.getInt(1);
            connection.createStatement().execute("insert into administrators (ProjectId, UserId) values (" + projectId + ", " + userId + ")");
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");
            System.out.println(projectId);
            for(Reward reward :project.getRewards()){
                this.createReward(reward, 0, projectId, userId);
            }
            for(Path path:project.getPaths()){
                this.createPath(path, 0, userId, projectId);
            }
            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean cancelProject(int projectId, int requestId, int userId) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            result = connection.createStatement().executeQuery("select count(*) from administrators where userId = " + userId + " and projectId= " + projectId);
            if(result.getInt(1) == 0)
                return false;

            result = connection.createStatement().executeQuery("select UserId, Value from transactions where ProjectId = " + projectId);
            while(result.next())
            {
                int pledgerId = result.getInt(1);
                double value = result.getDouble(2);
                double balance = connection.createStatement().executeQuery("select balance from users where id = " + pledgerId).getDouble(1);
                connection.createStatement().execute("update users set balance = " + (balance + value) + " where id = " + pledgerId);
            }

            connection.createStatement().execute("update projects set active = 0 where id = " + projectId);
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean endProject(int projectId) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select objective, OwnerUserId from projects where id = " + projectId);
        double objective = result.getDouble(1);
        int ownerUserId = result.getInt(2);
        double sum = connection.createStatement().executeQuery("select sum(value) from transactions where projectId = " + projectId).getDouble(1);
        if(sum<objective) {
            result = connection.createStatement().executeQuery("select UserId, Value from transactions where ProjectId = " + projectId);

            while(result.next())
            {
                int pledgerId = result.getInt(1);
                double value = result.getDouble(2);
                double balance = connection.createStatement().executeQuery("select balance from users where id = " + pledgerId).getDouble(1);
                connection.createStatement().execute("update users set balance = " + (balance + value) + " where id = " + pledgerId);
            }
            result = connection.createStatement().executeQuery("select id from rewards where projectId = " + projectId);
            while(result.next()){
                connection.createStatement().execute("delete from Rewards_Users where rewardId = " + result.getInt(1));
            }
            connection.createStatement().execute("update projects set active = 0 where id = " + projectId);
        }
        else {
            double balance = connection.createStatement().executeQuery("select balance from users where id = " + ownerUserId).getDouble(1);
            connection.createStatement().execute("update users set balance = " + (sum + balance) + " where id = " + ownerUserId);
            connection.createStatement().execute("update Rewards_Users set flag = 1 where projectId = " + projectId);
            connection.createStatement().execute("update projects set active = 0 where id = " + projectId);
        }
        return true;
    }

    public boolean financeProject(int projectId, int requestId, int userId, int pathId, double value) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            result = connection.createStatement().executeQuery("select balance from users where id = " + userId);
            if(result.getDouble(1) < value)
                return false;
            connection.createStatement().execute("update users set balance = " + (result.getDouble(1) - value) + " where id = " + userId);
            connection.createStatement().execute("insert into transactions (UserId, ProjectId,PathId, Value) values (" + userId + ", "+ +pathId+ ", " + projectId + ", " + value + ")");
            int rewardId = connection.createStatement().executeQuery("select id from rewards where projectId = " + projectId + " and MinValue = " + value).getInt(1);
            this.winReward(rewardId, 0, userId, 0);
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean addAdmin(int projectId, int requestId, int userId, int newAdminId) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            result = connection.createStatement().executeQuery("select * from administrators where userId = " + userId + " and projectId= " + projectId);

            if(!result.next())
                return false;

            result = connection.createStatement().executeQuery("select * from administrators where userId = " + newAdminId + " and projectId= " + projectId);
            if(result.next())
                return false;

            connection.createStatement().execute("insert into administrators (UserId, ProjectId) = " + newAdminId + " and projectId = " + projectId);
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean removeAdmin(int projectId, int requestId, int userId, int removeAdminId) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            result = connection.createStatement().executeQuery("select administrators.*, projects.OwnerUserId from administrators, projects where userId = " + userId + " and projectId= " + projectId + " and projects.OwnerUserId != " + removeAdminId);

            if(!result.next())
                return false;

            connection.createStatement().execute("delete from administrators where userId = " + removeAdminId + " and projectId = " + projectId);
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean sendMessage(Message message, int projectId, int requestId) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + message.getSender().getId());
        if(result.getInt(1) == 0){
            connection.createStatement().execute("insert into messages (ProjectId, Subject, Question, Response, FromUserId) values (" + projectId + ",\"" + message.getSubject() + "\",\"" + message.getQuestion() + "\", \"\", " + message.getSender().getId() + ")");
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + message.getSender().getId() + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + message.getSender().getId());
            return result.getBoolean(1);
        }
    }

    public boolean answerMessage(int messageId, String response, int requestId, int userId) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            int projectId = connection.createStatement().executeQuery("select projectId from messages where id = " + messageId + ")").getInt(1);
            result = connection.createStatement().executeQuery("select * from administrators where userId = " + userId + " and projectId= " + projectId);

            if(!result.next())
                return false;

            connection.createStatement().execute("update messages set response = " + response + " where id = " + messageId);
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean createPath(Path path, int requestId, int userId, int projectId) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0 || requestId == 0){
            result = connection.createStatement().executeQuery("select id from paths where Name = \"" + path.getName()+"\" and projectId = " + projectId);
            if(result.next())
                return false;

            result = connection.createStatement().executeQuery("select * from administrators where userId = " + userId + " and projectId= " + projectId);

            if(!result.next())
                return false;

            connection.createStatement().execute("insert into paths (Name, Description, ProjectId) values (\"" + path.getName() + "\", \"" + path.getDescription() + "\", " + projectId + ")");
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean deletePath(int pathId, int requestId, int userId) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            int projectId = connection.createStatement().executeQuery("select projectId from paths where id = " + pathId + ")").getInt(1);
            result = connection.createStatement().executeQuery("select * from administrators where userId = " + userId + " and projectId= " + projectId);

            if(!result.next())
                return false;

            connection.createStatement().execute("delete from paths where id = " + pathId);
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean createReward(Reward reward, int requestId, int projectId, int userId) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0 || requestId == 0){
            result = connection.createStatement().executeQuery("select id from rewards where Name = \"" + reward.getName()+"\" and projectId = " + projectId);
            if(result.next())
                return false;

            result = connection.createStatement().executeQuery("select * from administrators where userId = " + userId + " and projectId= " + projectId);
            if(!result.next())
                return false;
            System.out.println("insert into rewards (MinValue, Name, Description, ProjectId) values (" + reward.getMinValue()  + ",\"" + reward.getName() + "\", \"" + reward.getDescription() + "\", " + projectId + ")");
            connection.createStatement().execute("insert into rewards (MinValue, Name, Description, ProjectId) values (" + reward.getMinValue()  + ",\"" + reward.getName() + "\", \"" + reward.getDescription() + "\", " + projectId + ")");
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean removeReward(int rewardId, int requestId, int userId) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            int projectId = connection.createStatement().executeQuery("select projectId from rewards where id = " + rewardId + ")").getInt(1);
            result = connection.createStatement().executeQuery("select * from administrators where userId = " + userId + " and projectId= " + projectId);

            if(!result.next())
                return false;

            connection.createStatement().execute("delete from rewards where id = " + rewardId);
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean winReward(int rewardId, int requestId, int userId, int flag) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0 || requestId == 0){
            connection.createStatement().execute("insert into rewards_users (RewardId, WinnerUserId, OwnerUserId,flag) values (" + rewardId + ", " + userId + ", " + userId + ", " + flag + ")");
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean giveReward(int rewardId, int requestId, int userId, int receiverUserId, int flag) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0 || requestId == 0){
            connection.createStatement().execute("update rewards_users set OwnerUserId = " + receiverUserId + " where RewardId = " + rewardId);
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean createExtraLevel(Extra extra, int requestId, int projectId, int userId) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0 || requestId == 0){
            result = connection.createStatement().executeQuery("select id from extras where Name = \"" + extra.getName()+"\" and projectId = " + projectId);
            if(result.next())
                return false;

            result = connection.createStatement().executeQuery("select * from administrators where userId = " + userId + " and projectId= " + projectId);

            if(!result.next())
                return false;

            connection.createStatement().execute("insert into extras (MinValue, Name, Description, ProjectId) values (" + extra.getMinValue()  + ",\"" + extra.getName() + "\", \"" + extra.getDescription() + "\", " + projectId + ")");
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean removeExtraLevel(int extraId, int requestId, int userId) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            int projectId = connection.createStatement().executeQuery("select projectId from rewards where id = " + extraId + ")").getInt(1);
            result = connection.createStatement().executeQuery("select * from administrators where userId = " + userId + " and projectId= " + projectId);

            if(!result.next())
                return false;

            connection.createStatement().execute("delete from extras where id = " + extraId);
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }
}

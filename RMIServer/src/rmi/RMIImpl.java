package rmi; /**
 * Created by mariobalca on 24-10-2015.
 */

import genericclasses.*;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TumblrApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        ResultSet result = connection.createStatement().executeQuery("select * from users");
        ArrayList<User> users = new ArrayList<User>();
        while(result.next()){
            User user = new User(result.getString(2),result.getString(3));
            user.setId(result.getInt(1));
            users.add(user);
        }
        return users;
    }

    public User getUser(int userId) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("Select * from users where id = " + userId);
        User user;
        if(result.next()){
            user = new User(result.getString(2), result.getString(3));
            return user;
        }
        return null;
    }

    public ArrayList<User> getAdmins(int projectId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = connection.createStatement().executeQuery("Select * from Administrators where projectId = " + projectId);
        ArrayList<User> users = new ArrayList<User>();
        while(result.next())
        {
            int userId = result.getInt(3);
            System.out.println("Select * from users where userId = " + userId);
            ResultSet result2 = connection.createStatement().executeQuery("Select * from users where id = " + userId);
            User u = new User(result2.getString(2), result2.getString(3));
            u.setId(result2.getInt(1));
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
        System.out.println("Get Projects without Details Executed");
        return projects;
    }

    public ArrayList<Project> getProjects(String query) throws java.rmi.RemoteException, SQLException{
        ResultSet result = connection.createStatement().executeQuery(query);
        ArrayList<Project> projects = new ArrayList<Project>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        while(result.next())
        {
            try {
                System.out.printf(result.getString(3));
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
        return projects;
    }

    public Project getProject(int id) throws java.rmi.RemoteException, SQLException {
        String query = "select * from projects where id = " + id;
        Project result = getProjects(query).get(0);

        return result;
    }

    public ArrayList<Project> getCurrentProjects() throws java.rmi.RemoteException, SQLException{
        String query = "Select * from Projects where active = 1";
        ArrayList<Project> projects = getProjects(query);
        System.out.println("Get Current Projects executed");
        return projects;
    }

    public ArrayList<Project> getOlderProjects() throws java.rmi.RemoteException, SQLException{
        String query = "Select * from Projects where active = 0";
        ArrayList<Project> projects = getProjects(query);
        System.out.println("Get Older Projects executed");
        return projects;
    }

    public ArrayList<Project> getOwnedProjects(int userId) throws java.rmi.RemoteException, SQLException{
        String query = "Select * from Projects where OwnerUserId = " + userId;
        ArrayList<Project> projects = getProjects(query);
        System.out.println("Get Owned Projects executed");
        return projects;
    }

    public ArrayList<Project> getAdminProjects(int userId) throws java.rmi.RemoteException, SQLException{
        String query = "Select Projects.* from Projects, Administrators where UserId = " + userId + " and Projects.id = ProjectId and active = 1";
        ArrayList<Project> projects = getProjects(query);
        System.out.println("Get Administrated Projects executed");
        for (Project project:projects){
            System.out.println(project);
        }
        return projects;
    }

    public ArrayList<Reward> getProjectRewards(int projectId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = connection.createStatement().executeQuery("Select * from Rewards where ProjectId = " + projectId);
        ArrayList<Reward> rewards = new ArrayList<Reward>();

        while(result.next()){
            Reward r = new Reward(result.getDouble(2), result.getString(3), result.getString(4));
            r.setId(result.getInt(1));
            rewards.add(r);
        }
        System.out.println("Get Project Rewards executed");
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
        System.out.println("Get Project Extra Levels executed");
        return extraRewards;
    }

    public ArrayList<Reward> getUserRewards(int userId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = connection.createStatement().executeQuery("SELECT Rewards_Users.flag, Rewards_Users.id, Rewards.* from Rewards_Users INNER JOIN Rewards ON Rewards.id=Rewards_Users.rewardId where Rewards_Users.OwnerUserId = " + userId);
        ArrayList<Reward> rewards = new ArrayList<Reward>();

        while(result.next()){
            Reward r = new Reward(result.getDouble(4), result.getString(5), result.getString(6));
            r.setFlag(result.getBoolean(1));
            r.setId(result.getInt(2));
            rewards.add(r);
        }
        System.out.println("Get User Rewards executed");
        return rewards;
    }

    public ArrayList<Path> getProjectPaths(int projectId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = connection.createStatement().executeQuery("SELECT * from paths where projectId = " + projectId);
        ArrayList<Path> paths = new ArrayList<Path>();
        while(result.next())
        {
            int pathId = result.getInt(1);
            ResultSet result2 = connection.createStatement().executeQuery("Select sum(value) from transactions where PathId  = "+ pathId);
            Path p = new Path(result.getString(2), result.getString(4), result2.getDouble(1));
            p.setId(result.getInt(1));
            paths.add(p);
        }
        System.out.println("Get Project Paths executed");
        return paths;
    }

    public ArrayList<Message> getProjectMessages(int projectId) throws java.rmi.RemoteException, SQLException{
        ResultSet result = connection.createStatement().executeQuery("select messages.id, subject, question, response, users.username from messages inner join users on fromUserId = users.id where projectId = " + projectId);
        ArrayList<Message> messages = new ArrayList<Message>();
        while(result.next())
        {
            String subject = result.getString(2);
            String question = result.getString(3);
            String response = result.getString(4);

            Message m = new Message(subject, question, response);
            m.setFromUserUsername(result.getString(5));
            m.setId(result.getInt(1));
            messages.add(m);
        }
        System.out.println("Get Project Messages executed");
        return messages;
    }

    public double getBalance(int userId) throws java.rmi.RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select Balance from Users where id = " + userId);
        double balance = result.getDouble(1);

        System.out.println("Get User Balance executed");
        return balance;
    }

    public int[] loginUser(String username, String password) throws SQLException {
        System.out.println(username + " " + password);
        ResultSet result = connection.createStatement().executeQuery("select id from Users where username = \"" + username + "\" and password = \"" + password + "\"");
        if(result.next()){
            int userId = result.getInt(1);
            result = connection.createStatement().executeQuery("select max(requestId) from Logs where userId = " + userId);
            return new int[]{userId, result.getInt(1)};
        }
        else{
            return new int[]{0, 0};
        }
    }

    public double getProjectValue(int projectId) throws java.rmi.RemoteException,SQLException{
        ResultSet resultset = connection.createStatement().executeQuery("select sum(value) from transactions where projectId = " + projectId);
        return resultset.getDouble(1);
    }


    // NÃO IDEMPOTENTES

    public String signInTumblr() throws RemoteException{
        RMIServer.request_token = RMIServer.service.getRequestToken();
        String result = RMIServer.service.getAuthorizationUrl(RMIServer.request_token);
        return result;
    }


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

    public boolean createTumblrUser(String oauth_token,String oauth_verifier) throws RemoteException, SQLException {
        Verifier verifier = new Verifier(oauth_verifier);
        Token accessToken = RMIServer.service.getAccessToken(RMIServer.request_token,verifier);
        String user_token = accessToken.getToken();
        String user_secret = accessToken.getSecret();
        Token newToken = new Token(user_token,user_secret);
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.tumblr.com/v2/user/info");
        request.addHeader("Accept", "application/json");
        RMIServer.service.signRequest(newToken, request);
        System.out.println(request.getHeaders().keySet());
        org.scribe.model.Response response = request.send();
        JSONObject jsonresponse = new JSONObject(response.getBody());
        String name = jsonresponse.getJSONObject("response").getJSONObject("user").getString("name");
        ResultSet resultSet = connection.createStatement().executeQuery("select * from users where username = \""+name+"\"");
        if(resultSet.next()) {
            connection.createStatement().execute("update users set userToken = \"" + user_token + "\" , userSecret = \""+user_secret+"\" where username = \"" + name+"\"");
        }
        else {
            connection.createStatement().execute("insert into users (username,password, balance , userToken, userSecret) values (\"" + name + "\"," + "\"tumblr\"," + 100 + ", " + "\"" + user_token + "\", " + "\"" + user_secret + "\")");
        }
        return true;
    }


    public boolean isTumblr(String username) throws SQLException,RemoteException {
        ResultSet resultSet = connection.createStatement().executeQuery("select userToken,userSecret from users where username = \"" +username+"\"");
        if(resultSet.getString(1) == null){
            return false;
        }
        return true;
    }

    public boolean postinTumblr(String username,Project project) throws SQLException,RemoteException {
        ResultSet result = connection.createStatement().executeQuery("select userToken,userSecret from users where username = \"" +username+"\"");
        Token token = new Token(result.getString(1),result.getString(2));
        OAuthRequest request = new OAuthRequest(Verb.POST,"http://api.tumblr.com/v2/blog/"+username+".tumblr.com/post");
        request.addHeader("Accept", "application/json");
        request.addBodyParameter("type","text");
        request.addBodyParameter("body","Criei projeto " + project.getName());
        RMIServer.service.signRequest(token,request);
        org.scribe.model.Response response = request.send();
        System.out.println(response.getBody());
        Long postId = new JSONObject(response.getBody()).getJSONObject("response").getLong("id");
        System.out.println(postId);
        OAuthRequest request2 = new OAuthRequest(Verb.POST,"http://api.tumblr.com/v2/blog/"+username+".tumblr.com/posts");
        request.addHeader("Accept", "application/json");
        request.addBodyParameter("type","text");
        request.addBodyParameter("id", String.valueOf(project.getId()));
        RMIServer.service.signRequest(token,request2);
        org.scribe.model.Response response2 = request2.send();
        System.out.println(response2.getBody());
        String reblog_key = new JSONObject(response2.getBody()).getJSONObject("response").getString("reblog_key");
        System.out.println("update projects set TumblrPostId = "+postId+" where id = "+ project.getId());
        connection.createStatement().execute("update projects set TumblrPostId = "+postId+",reblog_key = \"" + reblog_key+"\" whessre id = "+ project.getId());
        return true;
    }


    public boolean createProject(Project project, int requestId, int userId) throws RemoteException, SQLException {
        System.out.println(userId);
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            result = connection.createStatement().executeQuery("select id from projects where Name = \"" + project.getName()+"\"");
            if(result.next()) {
                return false;
            }
            connection.setAutoCommit(false);
            connection.createStatement().execute("insert into projects (Name, Deadline, Objective, Description, OwnerUserId,Active) values (\"" + project.getName() + "\", \"" + dateFormat.format(project.getDeadline()) + "\"," + project.getObjective() + ", \"" + project.getDescription() + "\", " + userId + "," +1+ ")");
            result = connection.createStatement().executeQuery("select id from projects where Name = \"" + project.getName()+"\"");
            int projectId = result.getInt(1);
            connection.createStatement().execute("insert into administrators (ProjectId, UserId) values (" + projectId + ", " + userId + ")");
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");

            for(Reward reward :project.getRewards()){
                this.createReward(reward, 0, projectId, userId);
            }
            for(Path path:project.getPaths()){
                this.createPath(path, 0, userId, projectId);
            }
            project.setId(projectId);
            System.out.println("Created Proj");
            User username = getUser(userId);
            if(isTumblr(username.getUsername())) {
                postinTumblr(username.getUsername(),project);
            }
            connection.commit();
            return true;
        }

        else{
            System.out.println("OUPS");
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
            connection.setAutoCommit(false);
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
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");
            connection.commit();
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
        connection.setAutoCommit(false);
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
            ResultSet rewardsRS = connection.createStatement().executeQuery("select id from Rewards where projectId = " + projectId);
            while(rewardsRS.next()){
                connection.createStatement().execute("update Rewards_Users set flag = 1 where rewardId = " + rewardsRS.getInt(1));
            }
            connection.createStatement().execute("update projects set active = 0 where id = " + projectId);
        }
        connection.commit();
        return true;
    }

    public boolean financeProject(int projectId, int requestId, int userId, int pathId, double value) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            result = connection.createStatement().executeQuery("select balance from users where id = " + userId);
            if(result.getDouble(1) < value)
                return false;

            ResultSet rewardIdRS = connection.createStatement().executeQuery("select id from rewards where projectId = " + projectId + " and MinValue = " + value);
            if(!rewardIdRS.next())
                return false;
            connection.setAutoCommit(false);
            connection.createStatement().execute("update users set balance = " + (result.getDouble(1) - value) + " where id = " + userId);
            connection.createStatement().execute("insert into transactions (UserId, ProjectId,PathId, Value) values (" + userId + ", " +projectId  + ", " + pathId+ ", " + value + ")");

            this.winReward(rewardIdRS.getInt(1), 0, userId, 0);
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");
            if(isTumblr(getUser(userId).getUsername())){
                likeProject(getUser(userId).getUsername(),projectId);
            }
            connection.commit();
            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public void likeProject(String username, int projectId) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select userToken,userSecret from users where username = \"" +username+"\"");
        Token token = new Token(result.getString(1),result.getString(2));
        ResultSet result2 = connection.createStatement().executeQuery("select TumblrPostId ,Reblog_key from projects where id = " + projectId);
        if(result2.getString(1)!=null){
            OAuthRequest request = new OAuthRequest(Verb.POST,"http://api.tumblr.com/v2/user/like");
            request.addHeader("Accept", "application/json");
            request.addBodyParameter("id", String.valueOf(result2.getLong(1)));
            request.addBodyParameter("reblog_key",result2.getString(2));
            RMIServer.service.signRequest(token,request);
            org.scribe.model.Response response = request.send();
            System.out.println(response.getBody());
        }
    }

    public boolean addAdmin(int projectId, int requestId, int userId, String newAdminName) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            result = connection.createStatement().executeQuery("select * from administrators where userId = " + userId + " and projectId= " + projectId);

            if(!result.next())
                return false;

            ResultSet resultSet = connection.createStatement().executeQuery("select id from users where username = \"" + newAdminName + "\"");
            if(!resultSet.next())
                return false;

            int newAdminId = resultSet.getInt(1);

            result = connection.createStatement().executeQuery("select * from administrators where userId = " + newAdminId + " and projectId= " + projectId);
            if(result.next())
                return false;
            connection.setAutoCommit(false);
            connection.createStatement().execute("insert into administrators (UserId, ProjectId) values (" + newAdminId + ", " + projectId+ ")");
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");
            connection.commit();
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
            connection.setAutoCommit(false);
            connection.createStatement().execute("delete from administrators where userId = " + removeAdminId + " and projectId = " + projectId);
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");
            connection.commit();
            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean sendMessage(Message message, int projectId, int requestId) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + message.getFromUserId());
        if(result.getInt(1) == 0){
            connection.setAutoCommit(false);
            System.out.println("cheguei aqui");
            connection.createStatement().execute("insert into messages (ProjectId, Subject, Question, Response, FromUserId) values (" + projectId + ",\"" + message.getSubject() + "\",\"" + message.getQuestion() + "\", \"\", " + message.getFromUserId() + ")");
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + message.getFromUserId() + ", " + requestId + ", 1)");
            connection.commit();
            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + message.getFromUserId());
            return result.getBoolean(1);
        }
    }

    public boolean answerMessage(int messageId, String response, int requestId, int userId) throws RemoteException, SQLException {
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            result = connection.createStatement().executeQuery("select * from administrators where userId = " + userId + " and projectId = (select projectId from messages where id = " + messageId + ")");

            if(!result.next())
                return false;
            connection.setAutoCommit(false);
            connection.createStatement().execute("update messages set response = \"" + response + "\" where id = " + messageId);
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");
            connection.commit();
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

    public boolean createReward(Reward reward, int requestId, int projectId, int userId) throws RemoteException, SQLException {
        System.out.println(reward + " " + requestId + " " + projectId + " " + userId);
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0 || requestId == 0){
            result = connection.createStatement().executeQuery("select id from rewards where Name = \"" + reward.getName()+"\" and projectId = " + projectId);
            if(result.next())
                return false;

            result = connection.createStatement().executeQuery("select * from administrators where userId = " + userId + " and projectId= " + projectId);
            if(!result.next())
                return false;
            connection.setAutoCommit(false);
            connection.createStatement().execute("insert into rewards (MinValue, Name, Description, ProjectId) values (" + reward.getMinValue()  + ",\"" + reward.getName() + "\", \"" + reward.getDescription() + "\", " + projectId + ")");
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");
            connection.commit();
            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean removeReward(int rewardId, int requestId, int userId) throws RemoteException, SQLException {
        System.out.println(rewardId + " " + requestId + " " + userId);
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0){
            result = connection.createStatement().executeQuery("select * from administrators where projectId = (select projectid from rewards where id = " + rewardId + ")");

            if(!result.next())
                return false;
            connection.setAutoCommit(false);
            connection.createStatement().execute("delete from rewards where id = " + rewardId);
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");
            connection.commit();
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
            connection.setAutoCommit(false);
            connection.createStatement().execute("insert into rewards_users (RewardId, WinnerUserId, OwnerUserId,flag) values (" + rewardId + ", " + userId + ", " + userId + ", " + flag + ")");
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");
            connection.commit();
            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }

    public boolean giveReward(int rewardUserId, int requestId, int userId, String receiverUserName, int flag) throws RemoteException, SQLException {
        System.out.println(rewardUserId+ " " + requestId+ " " + userId + " " + receiverUserName);
        ResultSet result = connection.createStatement().executeQuery("select count(*) from logs where requestId = " + requestId + " and userId = " + userId);
        if(result.getInt(1) == 0 || requestId == 0){
            ResultSet resultSet = connection.createStatement().executeQuery("select id from users where username = \"" + receiverUserName + "\"");
            if(!resultSet.next())
                return false;
            int receiverUserId = resultSet.getInt(1);
            connection.setAutoCommit(false);
            connection.createStatement().execute("update rewards_users set OwnerUserId = " + receiverUserId + " where id = " + rewardUserId);
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");
            connection.commit();
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
            connection.setAutoCommit(false);
            connection.createStatement().execute("insert into extras (MinValue, Name, Description, ProjectId) values (" + extra.getMinValue()  + ",\"" + extra.getName() + "\", \"" + extra.getDescription() + "\", " + projectId + ")");
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");
            connection.commit();
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
            result = connection.createStatement().executeQuery("select * from administrators where projectId = (select projectid from extras where id = " + extraId + ")");

            if(!result.next())
                return false;
            connection.setAutoCommit(false);
            connection.createStatement().execute("delete from extras where id = " + extraId);
            connection.createStatement().execute("insert into logs (UserId, RequestId, Response) values (" + userId + ", " + requestId + ", 1)");
            connection.commit();

            return true;
        }
        else{
            result = connection.createStatement().executeQuery("select response from logs where requestId = " + requestId + " and userId = " + userId);
            return result.getBoolean(1);
        }
    }
}

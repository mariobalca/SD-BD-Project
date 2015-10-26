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
        ResultSet result = statement.executeQuery("Select * from users");
        String returnValue = "";
        ArrayList<User> users = new ArrayList<User>();
        while(result.next())
        {
            users.add(new User(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4)));
        }
        System.out.println("Get users executed");
        return users;
    }
}

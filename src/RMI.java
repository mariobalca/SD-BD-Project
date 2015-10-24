/**
 * Created by mariobalca on 24-10-2015.
 */

import java.io.Serializable;
import java.rmi.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RMI extends Remote, Serializable{
    public ArrayList<User> getUsers() throws java.rmi.RemoteException, SQLException;
}
/**
 * Created by mariobalca on 24-10-2015.
 */

import java.rmi.*;
public interface RMI extends Remote {
    public String sayHello() throws java.rmi.RemoteException;
}
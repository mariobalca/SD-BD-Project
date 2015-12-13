package actions.Rewards;

import genericclasses.Reward;
import org.apache.struts2.interceptor.SessionAware;
import rmi.RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Rui on 10/12/2015.
 */
public class ListRewardsAction implements SessionAware {
    private Map<String,Object> session;
    private ArrayList<Reward> rewards;
    private double balance;
    public String execute(){
        try {
            RMI rmi = getRMI();
            rewards = rmi.getUserRewards((int)session.get("usernameId"));
            balance = rmi.getBalance((int)session.get("usernameId"));
            return "success";
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "rmidown";
    }

    public ArrayList<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(ArrayList<Reward> rewards) {
        this.rewards = rewards;
    }

    public RMI getRMI() throws RemoteException, NotBoundException {
        if(!session.containsKey("rmi")){
            RMI rmi = (RMI) LocateRegistry.getRegistry("localhost",7000).lookup("rmi");
            setRMI(rmi);
        }
        return (RMI)session.get("rmi");
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setRMI(RMI rmi){
        session.put("rmi",rmi);
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}

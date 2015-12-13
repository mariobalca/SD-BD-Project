package actions.Rewards;

import org.apache.struts2.interceptor.SessionAware;
import rmi.RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Rui on 10/12/2015.
 */
public class GiveRewardAction implements SessionAware{
    private Map<String,Object> session;
    private int rewardId;
    private String username;

    public String execute(){
        try {
            RMI rmi = getRMI();
            int requestId = (int)session.get("requestId")+1;
            rmi.giveReward(rewardId,requestId,(int)session.get("usernameId"),username,420);
            System.out.println();
            session.put("requestId",requestId);
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

    public int getRewardId() {
        return rewardId;
    }

    public void setRewardId(int rewardId) {
        this.rewardId = rewardId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public RMI getRMI() throws RemoteException, NotBoundException {
        if(!session.containsKey("rmi")){
            RMI rmi = (RMI) LocateRegistry.getRegistry("localhost",7000).lookup("rmi");
            setRMI(rmi);
        }
        return (RMI)session.get("rmi");
    }

    public void setRMI(RMI rmi){
        session.put("rmi",rmi);
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}

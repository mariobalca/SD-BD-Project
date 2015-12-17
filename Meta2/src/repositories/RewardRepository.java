package repositories;

import genericclasses.Extra;
import genericclasses.Reward;
import rmi.RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Rui on 17/12/2015.
 */
public class RewardRepository {
    private RMI rmiServer;

    public RewardRepository(){
        try {
            this.rmiServer = (RMI) LocateRegistry.getRegistry("localhost",7000).lookup("rmi");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public boolean createReward(Reward reward, int requestId, int projectId, int userId){
        boolean successful;
        try {
            successful = rmiServer.createReward(reward,requestId,projectId,userId);
        } catch (RemoteException e) {
            successful = false;
        } catch (SQLException e) {
            successful = false;
        }
        return successful;
    }

    public boolean giveReward(int rewardUserId, int requestId, int userId, String receiverUserName){
        boolean successful;
        try {
            successful = rmiServer.giveReward(rewardUserId,requestId,userId,receiverUserName,0);
        } catch (RemoteException e) {
            successful = false;
        } catch (SQLException e) {
            successful = false;
        }
        return successful;
    }

    public boolean winReward(int rewardId, int requestId, int userId){
        boolean successful;
        try {
            successful = rmiServer.winReward(rewardId,requestId,userId,0);
        } catch (RemoteException e) {
            successful = false;
        } catch (SQLException e) {
            successful = false;
        }
        return successful;
    }

    public ArrayList<Reward> getUserRewards(int userId){
        ArrayList<Reward> rewards;
        try {
            rewards = rmiServer.getUserRewards(userId);
            return rewards;
        } catch (RemoteException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

}

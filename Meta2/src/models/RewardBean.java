package models;

import genericclasses.Reward;
import rmi.RMI;
import rmi.RMIServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by pedrocb on 16/12/2015.
 */
public class RewardBean {
    private RMI server;
    private int id;
    private double minValue;
    private String name;
    private String description;
    private boolean flag;

    public RewardBean() {
        try {
            server = (RMI) LocateRegistry.getRegistry("localhost",7000).lookup("rmi");
        }
        catch(NotBoundException | RemoteException e) {
            e.printStackTrace(); // what happens *after* we reach this line?
        }
    }

    private ArrayList<Reward> getUserRewards(int userId) throws RemoteException, SQLException {
        return server.getUserRewards(userId);
    }

    private ArrayList<Reward> getProjectRewards(int projectId) throws RemoteException, SQLException {
        return server.getProjectRewards(projectId);
    }

    public boolean createReward(int userId, int requestId, int projectId) throws RemoteException, SQLException {
        return server.createReward(new Reward(this.minValue,this.name,this.description),requestId,projectId,userId);
    }

    public boolean removeReward(int requestId, int userId) throws RemoteException, SQLException {
        return server.removeReward(this.id,requestId,userId);
    }

    public boolean winReward(int requestId, int userId) throws RemoteException, SQLException {
        return server.winReward(this.id,requestId,userId,(flag) ? 1 : 0);
    }

    public boolean giveReward(int giverUserId, String receiverUserName,int flag,int requestId) throws RemoteException, SQLException {
        return server.giveReward(this.id, requestId, giverUserId,receiverUserName,flag);
    }

}


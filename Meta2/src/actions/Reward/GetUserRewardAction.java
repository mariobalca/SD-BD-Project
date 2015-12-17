package actions.Reward;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.Reward;
import repositories.RewardRepository;

import java.util.ArrayList;

/**
 * Created by Rui on 17/12/2015.
 */
public class GetUserRewardAction extends ActionSupport {
    private int userId;
    private ArrayList<Reward> rewards;

    public String execute(){
        RewardRepository rewardRepository = new RewardRepository();
        rewards = rewardRepository.getUserRewards(userId);
        if(rewards != null){
            return SUCCESS;
        }
        else{
            return ERROR;
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(ArrayList<Reward> rewards) {
        this.rewards = rewards;
    }
}

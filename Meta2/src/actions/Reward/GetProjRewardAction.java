package actions.Reward;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.Reward;
import repositories.RewardRepository;

import java.util.ArrayList;

/**
 * Created by Rui on 18/12/2015.
 */
public class GetProjRewardAction extends ActionSupport{
    private int projId;
    private ArrayList<Reward> rewards;

    public String execute(){
        RewardRepository rewardRepository = new RewardRepository();
        rewards = rewardRepository.getProjectRewards(projId);
        return SUCCESS;
    }

    public int getProjId() {
        return projId;
    }

    public void setProjId(int projId) {
        this.projId = projId;
    }

    public ArrayList<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(ArrayList<Reward> rewards) {
        this.rewards = rewards;
    }
}

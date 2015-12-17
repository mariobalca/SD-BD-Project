package actions.Reward;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.Reward;
import repositories.RewardRepository;

/**
 * Created by Rui on 17/12/2015.
 */
public class CreateRewardAction extends ActionSupport {
    private Reward reward = new Reward();
    private int requestId, projectId, userId;

    public String execute(){
        RewardRepository rewardRepository = new RewardRepository();
        if (rewardRepository.createReward(reward,requestId,projectId,userId)){
            return SUCCESS;
        }
        else{
            return ERROR;
        }
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

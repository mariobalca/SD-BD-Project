package actions.Reward;

import com.opensymphony.xwork2.ActionSupport;
import org.omg.PortableInterceptor.SUCCESSFUL;
import repositories.RewardRepository;

/**
 * Created by Rui on 18/12/2015.
 */
public class RemoveRewardAction extends ActionSupport{
    private int rewardId,requestId, userId;

    public String execute(){
        RewardRepository rewardRepository = new RewardRepository();
        rewardRepository.removeReward(rewardId,requestId,userId);
        return SUCCESS;
    }

    public int getRewardId() {
        return rewardId;
    }

    public void setRewardId(int rewardId) {
        this.rewardId = rewardId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

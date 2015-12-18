package actions.Reward;

import com.opensymphony.xwork2.ActionSupport;
import genericclasses.JsonResponse;
import genericclasses.Reward;
import repositories.RewardRepository;

/**
 * Created by Rui on 17/12/2015.
 */
public class GiveRewardAction extends ActionSupport {
    private int rewardUserId, requestId, userId;
    private String receiverUserName;
    private JsonResponse response = new JsonResponse();

    public String execute(){
        RewardRepository rewardRepository = new RewardRepository();
        response.setSuccess(rewardRepository.giveReward(rewardUserId,requestId,userId,receiverUserName));
        return SUCCESS;
    }


    public int getRewardUserId() {
        return rewardUserId;
    }

    public void setRewardUserId(int rewardUserId) {
        this.rewardUserId = rewardUserId;
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

    public String getReceiverUserName() {
        return receiverUserName;
    }

    public void setReceiverUserName(String receiverUserName) {
        this.receiverUserName = receiverUserName;
    }

    public JsonResponse getResponse() {
        return response;
    }

    public void setResponse(JsonResponse response) {
        this.response = response;
    }
}

package genericclasses;

import java.util.ArrayList;

/**
 * Created by pedro on 31-10-2015.
 */
public class RewardsResponse extends Response{

    public ArrayList<Reward> rewards;
    public RewardsResponse(String response, ArrayList<Reward> rewards) {
        super(response);
        this.rewards = rewards;
    }
}

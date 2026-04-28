package com.example;

public abstract class PremiumClient extends Client{
    private boolean rewardsProgramMember;
    public PremiumClient(String clientID, String username, String password, Date dateLastOpened, Date dateOpened,
            boolean rewardsProgramMember) {
        super(clientID, username, password, dateLastOpened, dateOpened);
        this.rewardsProgramMember = rewardsProgramMember;
    }
    public boolean isRewardsProgramMember() {
        return rewardsProgramMember;
    }
    public void setRewardsProgramMember(boolean rewardsProgramMember) {
        this.rewardsProgramMember = rewardsProgramMember;
    }
}
package com.example;

public abstract class PremiumClient extends Client{
    public PremiumClient(String username, String password,boolean rewardsProgramMember) {
        super(username, password);
        this.rewardsProgramMember=rewardsProgramMember;
    }
    private boolean rewardsProgramMember;
    
    public boolean isRewardsProgramMember() {
        return rewardsProgramMember;
    }
    public void setRewardsProgramMember(boolean rewardsProgramMember) {
        this.rewardsProgramMember = rewardsProgramMember;
    }
}
package com.example;

import java.time.LocalDate;
public abstract class PremiumClient extends Client{
    private boolean rewardsProgramMember;
    public PremiumClient(String clientID, String username, String password, LocalDate dateLastOpened, LocalDate dateOpened,
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
package com.example;
/**
 * Type of client which may opt into the rewards program.
 * @author Mathieu Bernardin
 */
public abstract class PremiumClient extends Client{
    /**
     * If this user has opted into the rewards program.
     */
    private boolean rewardsProgramMember;
    /**
     * Constructor for this class.
     * @param username - this client's username.
     * @param password - this client's password.
     * @param rewardsProgramMember - if this client has opted into the rewards program.
     */
    public PremiumClient(String username, String password,boolean rewardsProgramMember) {
        super(username, password);
        this.rewardsProgramMember=rewardsProgramMember;
    }
    /**
     * Constructor for this class which bypasses duplicate ID check. Otherwise identical to main constructor for this class.
     * @param username - this client's username.
     * @param password - this client's password.
     * @param test - if this will be used for a test.
     * @param rewardsProgramMember - if this client has opted into the rewards program.
     */
    public PremiumClient(String username, String password, boolean test, boolean rewardsProgramMember) {
        super(username, password, test);
        this.rewardsProgramMember = rewardsProgramMember;
    }
    /**
     * Gets this client's reward program status.
     * @return if this client has opted into the rewards program.
     */
    public boolean isRewardsProgramMember() {
        return rewardsProgramMember;
    }
}
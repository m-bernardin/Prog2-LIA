package com.example;

public class VipClient extends PremiumClient{
    public VipClient(String username, String password, boolean rewardsProgramMember,String name,String contact) throws InvalidTypeException {
        super(username, password, rewardsProgramMember);
        setClientID(IdCreator.createID(1,4));
        monthlyFee=0;
    }
    public final static double EXTRA_INTEREST=0.01;
    private String name;
    private String contact;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
}
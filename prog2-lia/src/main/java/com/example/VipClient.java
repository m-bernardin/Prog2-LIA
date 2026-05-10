package com.example;
/**
 * A type of premium client which is a individual.
 * Earns extra interest on all owned earnings accounts and has account ownership fees waived.
 * @author Mathieu Bernardin
 */
public class VipClient extends PremiumClient{
    /**
     * The extra interest earned by this client.
     */
    public final static double EXTRA_INTEREST=0.01;
    /**
     * This client's name.
     */
    private String name;
    /**
     * This client's contact.
     */
    private String contact;
    /**
     * Constructor for this class. Automatically generates an ID (type (1,4)) and sets monthlyFees to 0.
     * @param username - this client's username.
     * @param password - this client's password.
     * @param rewardsProgramMember - if this client has opted into the rewards program.
     * @param name - this client's name.
     * @param contact - this client's contact.
     * @throws InvalidTypeException if somethin goes wrong.
     */
    public VipClient(String username, String password, boolean rewardsProgramMember,String name,String contact) throws InvalidTypeException {
        super(username, password, rewardsProgramMember);
        boolean validID=false;
        String ID="";
        while(!validID){
            ID=IdCreator.createID(1,4);
            if(!App.driver.exists(ID))validID=true;
        }
        setClientID(ID);
    }
    /**
     * Constructor for this class which bypasses duplicate ID check. Otherwise identical to main constructor for this class.
     * @param username - this client's username.
     * @param password - this client's password.
     * @param test - if this will be used for a test.
     * @param rewardsProgramMember - if this client has opted into the rewards program.
     * @param name - this client's name.
     * @param contact - this client's contact.
     * @throws InvalidTypeException if somethin goes wrong.
     */
    public VipClient(String username, String password, boolean test, boolean rewardsProgramMember, String name,String contact) throws InvalidTypeException {
        super(username, password, test, rewardsProgramMember);
        this.name = name;
        this.contact = contact;
        String ID="";
        if(!test){
            boolean validID=false;
            while(!validID){
                ID=IdCreator.createID(1,2);
                if(!App.driver.exists(ID))validID=true;
            }
        }
        else ID=IdCreator.createID(1,2);
        setClientID(ID);
    }
    /**
     * Gets this client's name.
     * @return this client's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets this client's contact.
     * @return this client's contact.
     */
    public String getContact() {
        return contact;
    }
}
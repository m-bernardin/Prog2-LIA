package com.example;

import java.util.ArrayList;
/**
 * Represents a client who is a corporation or business. Allows for multiple owners/managers.
 */
public class CorporateClient extends PremiumClient{
    private String companyName;
    private ArrayList<String> clientManagerContacts=new ArrayList<>();
    /**
     * Constructor for this class. Automatically generates an ID as IdCreator type (1,3).
     * @param username - the client's username.
     * @param password - the client's password.
     * @param rewardsProgramMember - if the client has opted into the rewards program.
     * @param companyName - the name of this company.
     * @param clientManagerContacts - list of contacts for this company's finance managers; Essentially those who may access this account.
     * @throws InvalidTypeException if the IdCreator recieved an invalid type.
     */
    public CorporateClient(String username, String password, boolean rewardsProgramMember,String companyName,ArrayList<String> clientManagerContacts) throws InvalidTypeException {
        super(username, password, rewardsProgramMember);
        this.companyName=companyName;
        this.clientManagerContacts=clientManagerContacts;
        boolean validID=false;
        String ID="";
        while(!validID){
            ID=IdCreator.createID(1,3);
            if(!App.driver.exists(ID))validID=true;
        }
        setClientID(ID);
    }
    /**
     * Gets the name of this company.
     * @return the name of this company.
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * Gets the list of contacts of this company's finance manager.
     * @return list of contacts of this company's finance manager.
     */
    public ArrayList<String> getClientManagerContacts() {
        return clientManagerContacts;
    }
    /**
     * Adds the specified manager to the list of finance managers for this company.
     * @param manager - the contact of the manager to be added.
     */
    public void addManager(String manager){
        clientManagerContacts.add(manager);
    }
    /**
     * Removes a specified manager from this company's list of finance managers.
     * @param manager - the manager to be removed.
     * @return true if the operation was successful; false otherwise.
     */
    public boolean removeManager(String manager){
        for(int i=0;i<clientManagerContacts.size();++i){
            if(clientManagerContacts.get(i).equals(manager)){
                clientManagerContacts.remove(i);
                return true;
            }
        }
        return false;
    }
}
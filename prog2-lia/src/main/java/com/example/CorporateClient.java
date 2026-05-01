package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
public class CorporateClient extends PremiumClient{
    private String companyName;
    private ArrayList<String> clientManagerContacts=new ArrayList<>();
    public CorporateClient(String clientID, String username, String password, LocalDate dateLastOpened, LocalDate dateOpened,
            boolean rewardsProgramMember, String companyName, ArrayList<String> clientManagerContacts) {
        super(clientID, username, password, dateLastOpened, dateOpened, rewardsProgramMember);
        this.companyName = companyName;
        this.clientManagerContacts = clientManagerContacts;
    }
    @Override
    public boolean applyMonthlyFee() {
        // TODO find way to interact with accounts directly
        throw new UnsupportedOperationException("Unimplemented method 'applyMonthlyFee'");
    }
    @Override
    protected boolean maintain() {
        applyMonthlyFee();
        // TODO find way to interact with accounts directly
        return true;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public ArrayList<String> getClientManagerContacts() {
        return clientManagerContacts;
    }
    public void setClientManagerContacts(ArrayList<String> clientManagerContacts) {
        this.clientManagerContacts = clientManagerContacts;
    }
    public boolean addManager(String manager){
        clientManagerContacts.add(manager);
        return true;
    }
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
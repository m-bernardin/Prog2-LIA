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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyMonthlyFee'");
    }
    @Override
    protected boolean maintain() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'maintain'");
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
        // TODO complete method
        throw new UnsupportedOperationException();
    }
    public boolean removeManager(String Manager){
        // TODO complete method
        throw new UnsupportedOperationException();
    }
}
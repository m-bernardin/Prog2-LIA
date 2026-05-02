package com.example;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
public abstract class Client implements Maintainable{
    // fields
    private String clientID;
    protected ArrayList<String> accounts=new ArrayList<>();
    private String username;
    private String password;
    protected ArrayList<String> transactions=new ArrayList<>();
    private LocalDate dateLastOpened;
    private LocalDate dateOpened;
    protected int monthlyFee=10;
    protected static final double EXTRA_INTEREST=0;
    // constructors
    public Client(String username,String password){
        this.username=username;
        this.password=password;
        accounts=new ArrayList<>();
        transactions=new ArrayList<>();
        clientID="XXXXXX";
        dateOpened=LocalDate.now();
    }
    // getters and setters
    public String getClientID() {
        return clientID;
    }
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
    public ArrayList<String> getAccounts() {
        return accounts;
    }
    public void setAccounts(ArrayList<String> accounts) {
        this.accounts = accounts;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public ArrayList<String> getTransactions() {
        return transactions;
    }
    public void setTransactions(ArrayList<String> transactions) {
        this.transactions = transactions;
    }
    public LocalDate getDateLastOpened() {
        return dateLastOpened;
    }
    public void setDateLastOpened(LocalDate dateLastOpened) {
        this.dateLastOpened = dateLastOpened;
    }
    public LocalDate getDateOpened() {
        return dateOpened;
    }
    public void setDateOpened(LocalDate dateOpened) {
        this.dateOpened = dateOpened;
    }
    public int getMonthlyFee() {
        return monthlyFee;
    }
    public void setMonthlyFee(int monthlyFee) {
        this.monthlyFee = monthlyFee;
    }
    // abstract methods
    protected boolean applyMonthlyFee(){
        try {
            return App.driver.withdraw(calculateTotalMonthlyFee(),App.driver.getChequing(getClientID()));
        } catch (NullPointerException e) {
            return false;
        }
    }
    // concrete methods
    protected boolean addAccount(String account){
        accounts.add(account);
        return true;
    }
    protected int calculateTotalMonthlyFee(){
        return monthlyFee*accounts.size();
    }
    @Override
    public boolean maintain() {
        Period periodSinceLastOpened=Period.between(LocalDate.now(), dateLastOpened);
        int monthsSinceLastOpened=periodSinceLastOpened.getMonths()+periodSinceLastOpened.getYears()*12;
        boolean sufficientFunds=true;
        for(int i=0;i<monthsSinceLastOpened;++i){
            sufficientFunds=applyMonthlyFee();
            for (String accountID : App.driver.getOwnedEarningsAccounts(clientID)) {
            try {
                App.driver.getEarningsAccount(accountID).applyInterest();
            } catch (Exception e) {
                System.out.println("**non eaarnings account");
            }
            }
        }
        setDateLastOpened(LocalDate.now());
        return sufficientFunds;
    }
}
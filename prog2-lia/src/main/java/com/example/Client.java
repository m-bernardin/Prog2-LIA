package com.example;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
public abstract class Client implements Maintainable{
    // fields
    private String clientID;
    protected HashSet<String> accounts=new HashSet<>();
    private String username;
    private String password;
    protected HashSet<String> transactions=new HashSet<>();
    private LocalDate dateLastOpened;
    private LocalDate dateOpened;
    protected int monthlyFee=10;
    protected static final double EXTRA_INTEREST=0;
    // constructors
    public Client(String username,String password){
        this.username=username;
        this.password=password;
        accounts=new HashSet<>();
        transactions=new HashSet<>();
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
    public HashSet<String> getAccounts() {
        return accounts;
    }
    public void setAccounts(HashSet<String> accounts) {
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
    public HashSet<String> getTransactions() {
        return transactions;
    }
    public void setTransactions(HashSet<String> transactions) {
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
    protected boolean applyMonthlyFee() throws InvalidTypeException{
        boolean success=true;
        try {
            App.driver.withdraw(calculateTotalMonthlyFee(),App.driver.getChequing(getClientID()));
        } catch (NullPointerException e) {
            App.displayError("No chequing account found to charge for monthly fees.");
            success=false;
        } catch (InvestmentLockException e) {
            App.displayError("System error: monthly fee was charged to locked investment account.");
            success=false;
        } catch (InsufficientFundsException e) {
            App.displayError("Insufficient funds to charge monthly fees");
        } return success;
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
    public boolean maintain() throws InvalidTypeException {
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
package com.example;
import java.util.ArrayList;
public abstract class Client implements Maintainable{
    // fields
    private String clientID;
    protected ArrayList<String> accounts=new ArrayList<>();
    private String username;
    private String password;
    protected ArrayList<String> transactions=new ArrayList<>();
    private Date dateLastOpened;
    private Date dateOpened;
    protected int monthlyFee=10;
    // constructors
    /**
     * constructor for new account
     * TODO finish javadoc
     * @param clientID
     * @param username
     * @param password
     * @param dateLastOpened
     * @param dateOpened
     */
    public Client(String clientID, String username, String password, Date dateLastOpened, Date dateOpened) {
        this.clientID = clientID;
        this.username = username;
        this.password = password;
        this.dateLastOpened = dateLastOpened;
        this.dateOpened = dateOpened;
    }
    public Client(String clientID, ArrayList<String> accounts, String username, String password,
            ArrayList<String> transactions, Date dateLastOpened, Date dateOpened, int monthlyFee) {
        this.clientID = clientID;
        this.accounts = accounts;
        this.username = username;
        this.password = password;
        this.transactions = transactions;
        this.dateLastOpened = dateLastOpened;
        this.dateOpened = dateOpened;
        this.monthlyFee = monthlyFee;
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
    public Date getDateLastOpened() {
        return dateLastOpened;
    }
    public void setDateLastOpened(Date dateLastOpened) {
        this.dateLastOpened = dateLastOpened;
    }
    public Date getDateOpened() {
        return dateOpened;
    }
    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }
    public int getMonthlyFee() {
        return monthlyFee;
    }
    public void setMonthlyFee(int monthlyFee) {
        this.monthlyFee = monthlyFee;
    }
    // abstract methods
    protected abstract boolean maintain();
    // concrete methods
    protected boolean addAccount(String account){
        // TODO complete method
        throw new UnsupportedOperationException();
    }
    protected int calculateTotalMonthlyFee(){
        // TODO complete method
        throw new UnsupportedOperationException();
    }
}
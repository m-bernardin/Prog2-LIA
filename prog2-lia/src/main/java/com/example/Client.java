package com.example;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
/**
 * Represents a client at its most basic form.
 * @author Mathieu Bernardin
 */
public abstract class Client implements Maintainable{
    /**
     * Percentage of extra interest applied to monthly interest on earnings accounts.
     */
    protected static final double EXTRA_INTEREST=0;
    /**
     * This client's unqiue ID.
     * IdCreator type 1.
     */
    private String clientID;
    /**
     * Set of the unqiue IDs of all accounts owned by this client.
     */
    protected ArrayList<String> accounts=new ArrayList<>();
    /**
     * This client's username, used for login.
     */
    private String username;
    /**
     * This client's password, used for login.
     */
    private String password;
    /**
     * Set of unique IDs of all transactions associated with this account.
     */
    protected HashSet<String> transactions=new HashSet<>();
    /**
     * The date that this client last logged in.
     */
    private LocalDate dateLastOpened;
    /**
     * The date that this client first logged in.
     */
    private LocalDate dateOpened;
    /**
     * The amount this client must pay each month per owned account.
     */
    protected int monthlyFee=10;
    /**
     * The basic constructor for this class. Takes a username and password, then auto-generates the rest.
     * @param username - this client's username.
     * @param password - this client's password.
     */
    public Client(String username,String password){
        this.username=username;
        this.password=password;
        accounts=new ArrayList<>();
        accounts.add("X");
        transactions=new HashSet<>();
        clientID="";
        dateOpened=LocalDate.now();
    }
    /**
     * Modified version of this class's basic constructor.
     * @param username - this client's username.
     * @param password - this client's password
     * @param test - if this will be used for a test.
     */
    public Client(String username, String password,boolean test) {
        this.username=username;
        this.password=password;
        accounts=new ArrayList<>();
        accounts.add("X");
        transactions=new HashSet<>();
        clientID="";
        dateOpened=LocalDate.now();
    }
    /**
     * Gets the fee this client must pay each per account each month.
     * @return the fee this client must pay each per account each month.
     */
    public int getMonthlyFee() {
        return monthlyFee;
    }
    /**
     * Sets the client's ID to the specified ID.
     * @param clientID - the ID to be changed to.
     */
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
    /**
     * Gets the client's username.
     * @return - the client's username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Gets the client's password.
     * @return - the client's password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets the date this client last logged in to the specified date.
     * @param dateLastOpened - the date to be changed to.
     */
    public void setDateLastOpened(LocalDate dateLastOpened) {
        this.dateLastOpened = dateLastOpened;
    }
    /**
     * Gets the client's ID.
     * @return the client's ID.
     */
    public String getClientID() {
        return clientID;
    }
    /**
     * Gets the client's owned accounts' IDs.
     * @return a set of the accounts' IDs.
     */
    public ArrayList<String> getAccounts() {
        return accounts;
    }
    /**
     * Implementation of the Maintainable interface. Defines what must be done each time this client logs in.
     * @return true if the maintenance is successful; false otherwise.
     * @throws InvalidTypeException if something went wrong on a deeper level.
     */
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
    /**
     * Charges monthly fees to this client's primary chequeing account.
     * @return true if this operation was successful; false otherwise.
     * @throws InvalidTypeException if something went wrong on a deeper level.
     */
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
    /**
     * Adds a account as an owned account, based off a specified account ID.
     * @param accountID - the account ID to be added.
     */
    protected void addAccount(String accountID){
        accounts.add(accountID);
    }
    /**
     * Gets the total amount this client must pay each omth for their open accounts.
     * @return the amount they must pay.
     */
    protected int calculateTotalMonthlyFee(){
        return monthlyFee*(accounts.size()-1);
    }
}
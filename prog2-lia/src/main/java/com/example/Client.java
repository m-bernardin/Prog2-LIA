package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
/**
 * Represents a client at its most basic form.
 * @author Mathieu Bernardin
 */
public abstract class Client{
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
     * The date that this client first logged in.
     */
    private LocalDate dateOpened;
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
     * Adds a account as an owned account, based off a specified account ID.
     * @param accountID - the account ID to be added.
     */
    protected void addAccount(String accountID){
        accounts.add(accountID);
    }
}
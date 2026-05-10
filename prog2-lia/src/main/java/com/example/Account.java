package com.example;
/**
 * This class represents the most generic user account.
 * @author Mathieu Bernardin
 */
public abstract class Account {
    /**
     * The current balance of this account.
     */
    protected double balance;
    /**
     * This accounts unique ID.
     * IdCreator type 2.
     */
    protected String accountID;
    // TODO javadoc
    private boolean premiumOwner=false;
    // TODO javadoc
    public void setPremiumOwner(boolean premiumOwner) {
        this.premiumOwner = premiumOwner;
    }
    /**
     * This classes basic constructor. Sets balance to 0, but leaves accountID blank.
     * @throws InvalidTypeException if somehow an invalid type or subtype was supplied by a child's constructor  to the IdCCreator.
     */
    public Account() throws InvalidTypeException{
        balance=0;
    }
    /**
     * Constructor for this class which bypasses duplicate ID check. Otherwise identical to main constructor for this class.
     * @param balance - this account's starting balance.
     * @throws InvalidTypeException if something goes wrong.
     */
    public Account(double balance) throws InvalidTypeException{
        this.balance = balance;
    }
    /**
     * Returns this accounts current balance.
     * @return balance
     */
    public double getBalance() {
        return balance;
    }
    /**
     * Returns this accounts ID.
     * @return accountID
     */
    public String getAccountID() {
        return accountID;
    }
    /**
     * Takes a specified amount away from this account's balance.
     * @param amnt - the amount to be withdrawn.
     * @throws InvestmentLockException if a user attempts to withdraw from an Investment account which has not been open for at least a year.
     * @throws InsufficientFundsException if a user attempts to withdraw more than this accounts current balance from this account 
     */
    public abstract void withdraw(double amnt) throws InvestmentLockException, InsufficientFundsException;
    /**
     * Adds the specified amount to this account's balance.
     * @param amnt - the amount to be deposited.
     * @return true if the deposit was successful; false otherwise.
     */
    public boolean deposit(double amnt){
        balance+=amnt;
        return true;
    }
    /**
     * Adds the specified amount to this account's balance, in the specified currency.
     * @param amnt - the amount to be deposited.
     * @param currencyCode - the ISO code for the currency to be converted from.
     * @return true if the deposit was successful; false otherwise.
     */
    public boolean deposit(double amnt,String currencyCode){
        if(!premiumOwner)return false;
        try {
            deposit(convert(amnt, currencyCode));
        } catch (InvalidTypeException e) {
            return false;
        }
        return true;
    }
    /**
     * Transfers a specified amount from this account to another specified account.
     * @param amnt - the amount to be transfered.
     * @param transferToID - the ID of the account to be transfered to.
     * @return true if the transfer was succesful; false otherwise.
     * @throws InvestmentLockException if this account is an Investment account which has not been open for more than a year.
     * @throws InsufficientFundsException if this account does not have enough funds for this transfer.
     * @throws InvalidTypeException if an invalid type was provided to the IdCreator by the Transaction record creator.
     */
    public boolean transfer(double amnt,String transferToID) throws InvestmentLockException, InsufficientFundsException, InvalidTypeException{
        withdraw(amnt);
        boolean validTransfer=App.driver.deposit(amnt,transferToID);
        if(!validTransfer){
            deposit(amnt);
            return false;
        }
        return true;
    }
    /**
     * Provides a String representation of this account.
     */
    @Override
    public String toString() {
        return "account ID: "+accountID+"\tbalance: "+balance+"$";
    }
    /**
     * Converts a specified amount to its equivalent in a specified currency.
     * Data pulled from https://www.bankofcanada.ca/rates/exchange/annual-average-exchange-rates/; averages for 2025
     * @param amnt - the amount to be converted.
     * @param currencyCode - the ISO currency code of the currency to be converted to.
     * @return the converted amount.
     * @throws InvalidTypeException if the specified ISO currency code is not recognized by the system.
     */
    public static double convert(double amnt,String currencyCode) throws InvalidTypeException{
        if(currencyCode.equals("USD"))return amnt*1.3978;
        if(currencyCode.equals("GBP"))return amnt*1.8420;
        if(currencyCode.equals("JPY"))return amnt*0.009350;
        if(currencyCode.equals("EUR"))return amnt*1.5782;
        if(currencyCode.equals("AUD"))return amnt*0.9009;
        if(currencyCode.equals("CHF"))return amnt*1.6846;
        throw new InvalidTypeException("Invalid currency code");
    }
}
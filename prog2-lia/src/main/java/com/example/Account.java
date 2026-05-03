package com.example;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Account {
    // fields
    protected final DoubleProperty balance=new SimpleDoubleProperty();
    private String accountID;
    // constructors
    public Account(double balance, String accountID) {
        setBalance(balance);
        this.accountID = accountID;
    }
    // getters and setters
    public double getBalance() {
        return balance.get();
    }
    public void setBalance(double balance) {
        this.balance.set(balance);
    }
    public String getAccountID() {
        return accountID;
    }
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }
    // abstract methods
    public abstract boolean withdraw(double amnt) throws InvestmentLockException;
    // concrete methods
    public boolean deposit(double amnt){
        // TODO find failure instance???
        boolean success=true;
        balance.add(amnt);
        return success;
    }
    public boolean deposit(double amnt,String currency){
        if(!App.driver.isPremium(App.driver.getOwner(this.accountID)))return false;
        try {
            deposit(convert(amnt, currency));
        } catch (InvalidTypeException e) {
            return false;
        }
        return true;
    }
    public boolean transfer(double amnt,String transferToID) throws InvestmentLockException{
        // TODO test
        withdraw(amnt);
        boolean validTransfer=App.driver.deposit(amnt,transferToID);
        if(!validTransfer){
            App.driver.deposit(amnt,this.accountID);
            return false;
        }
        return true;
    }
    // Data pulled from https://www.bankofcanada.ca/rates/exchange/annual-average-exchange-rates/; averages for 2025
    double convert(double amnt,String currencyCode) throws InvalidTypeException{
        if(currencyCode.equals("USD"))return amnt*1.3978;
        if(currencyCode.equals("GBP"))return amnt*1.8420;
        if(currencyCode.equals("JPY"))return amnt*0.009350;
        if(currencyCode.equals("EUR"))return amnt*1.5782;
        if(currencyCode.equals("AUD"))return amnt*0.9009;
        if(currencyCode.equals("CHF"))return amnt*1.6846;
        throw new InvalidTypeException("Invalid currency code");
    }
}
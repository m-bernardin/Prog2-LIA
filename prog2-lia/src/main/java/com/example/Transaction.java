package com.example;

public class Transaction{
    private String transactionID;
    private double amnt;
    private String receivingAccount;
    private String givingAccount;
    public Transaction(double amnt, String receivingAccount, String givingAccount) throws InvalidTypeException {
        transactionID=IdCreator.createID(3,0);
        this.amnt = amnt;
        this.receivingAccount = receivingAccount;
        this.givingAccount = givingAccount;
    }
    public String getTransactionID() {
        return transactionID;
    }
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
    public double getAmnt() {
        return amnt;
    }
    public void setAmnt(double amnt) {
        this.amnt = amnt;
    }
    public String getReceivingAccount() {
        return receivingAccount;
    }
    public void setReceivingAccount(String receivingAccount) {
        this.receivingAccount = receivingAccount;
    }
    public String getGivingAccount() {
        return givingAccount;
    }
    public void setGivingAccount(String givingAccount) {
        this.givingAccount = givingAccount;
    }
}
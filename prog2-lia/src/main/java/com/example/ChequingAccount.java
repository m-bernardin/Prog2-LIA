package com.example;

public class ChequingAccount extends Account{
    public ChequingAccount(double balance, String accountID) {
        super(balance, accountID);
    }
    @Override
    public boolean withdraw(double amnt) {
        if(balance.get()>=amnt){
            balance.subtract(amnt);
            return true;
        }
        return false;
    }
}
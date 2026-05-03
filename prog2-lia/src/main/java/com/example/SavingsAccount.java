package com.example;

public class SavingsAccount extends EarningsAccount{
    public SavingsAccount(double balance, String accountID) {
        super(balance, 0.02, accountID);
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
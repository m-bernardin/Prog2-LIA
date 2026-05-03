package com.example;

public class SavingsAccount extends EarningsAccount{
    public SavingsAccount(double balance, String accountID) {
        super(balance, 0.02, accountID);
    }
    @Override
    public void withdraw(double amnt) throws InsufficientFundsException {
        if(balance.get()>=amnt){
            balance.subtract(amnt);
            return;
        }
        throw new InsufficientFundsException();
    }
}
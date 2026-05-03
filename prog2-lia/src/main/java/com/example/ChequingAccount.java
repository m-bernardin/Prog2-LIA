package com.example;

public class ChequingAccount extends Account{
    public ChequingAccount(double balance, String accountID) {
        super(balance, accountID);
    }
    @Override
    public void withdraw(double amnt) throws InsufficientFundsException{
        if(balance.get()>=amnt){
            balance.subtract(amnt);
            return;
        }
        throw new InsufficientFundsException();
    }
}
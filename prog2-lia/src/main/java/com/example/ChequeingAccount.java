package com.example;

public class ChequeingAccount extends Account{
    public ChequeingAccount(double balance, String accountID) {
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
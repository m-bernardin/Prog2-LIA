package com.example;

public class ChequeingAccount extends Account{
    public ChequeingAccount() throws InvalidTypeException {
        accountID=IdCreator.createID(2,1);
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
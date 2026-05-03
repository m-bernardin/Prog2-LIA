package com.example;

public class SavingsAccount extends EarningsAccount{
    public SavingsAccount() throws InvalidTypeException {
        accountID=IdCreator.createID(2,2);
        interestRate=0.02;
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
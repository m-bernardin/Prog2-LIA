package com.example;

public class SavingsAccount extends EarningsAccount{
    public SavingsAccount() throws InvalidTypeException {
        super();
        boolean validID=false;
        while(!validID){
            accountID=IdCreator.createID(2,2);
            if(!App.driver.exists(accountID))validID=true;
        }
        interestRate=0.02;
    }
    @Override
    public void withdraw(double amnt) throws InsufficientFundsException {
        if(balance>=amnt){
            balance-=amnt;
            return;
        }
        throw new InsufficientFundsException();
    }
    @Override
    public String toString() {
        return "Savings account no. "+accountID+"\tbalance: "+balance+"$";
    }
}
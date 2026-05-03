package com.example;

public class BlankAccount extends Account{

    public BlankAccount() throws InvalidTypeException {
        super();
        accountID="X";
    }
    @Override
    public String toString() {
        return "No accounts to show...";
    }
    @Override
    public void withdraw(double amnt) throws InvestmentLockException, InsufficientFundsException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'withdraw'");
    }
}

package com.example;

public abstract class EarningsAccount extends Account implements InterestBearing{
    protected double interestRate;
    public EarningsAccount(double balance, double interestRate, String accountID) {
        super(balance, accountID);
        this.interestRate = interestRate;
    }
    public double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    @Override
    public boolean applyInterest() {
        // TODO find insatnce of failure?? (int overflow?)
        boolean success=true;
        balance+=balance*interestRate;
        return success;
    }
}
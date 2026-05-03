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
        // TODO find failure instance?
        boolean success=true;
        balance.add(balance.get()*(interestRate+App.driver.getClient(App.driver.getOwner(getAccountID())).EXTRA_INTEREST));
        double amnt=balance.get()*(interestRate+App.driver.getClient(App.driver.getOwner(getAccountID())).EXTRA_INTEREST);
        System.out.println("**amount added to "+getAccountID()+": "+amnt);
        return success;
    }
}
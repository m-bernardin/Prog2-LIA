package com.example;

public abstract class EarningsAccount extends Account implements InterestBearing{
    protected double interestRate;
    public EarningsAccount() throws InvalidTypeException {
        super();
    }
    public double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    @Override
    public void applyInterest() {
        balance+=balance*(interestRate+App.driver.getClient(App.driver.getOwner(getAccountID())).EXTRA_INTEREST);
    }
}
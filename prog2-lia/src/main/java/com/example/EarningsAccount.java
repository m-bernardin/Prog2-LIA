package com.example;
/**
 * Represents an account which can earn interest on its balance.
 * @author Mathieu Bernardin
 */
public abstract class EarningsAccount extends Account implements InterestBearing{
    /**
     * The rate at which this account earns interest.
     */
    protected double interestRate;
    /**
     * Constructor for this class. Identical to super implementation.
     * @see Account
     */
    public EarningsAccount() throws InvalidTypeException {
        super();
    }
    /**
     * Gets the rate at which this account earns interest.
     * @return this accounts interestRate.
     */
    public double getInterestRate() {
        return interestRate;
    }
    /**
     * Applies interest to this account.
     */
    @SuppressWarnings("static-access")
    @Override
    public void applyInterest() {
        balance+=balance*(interestRate+App.driver.getClient(App.driver.getOwner(getAccountID())).EXTRA_INTEREST);
    }
}
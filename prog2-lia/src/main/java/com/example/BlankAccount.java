package com.example;
/**
 * A child of Account which describes an account with no details. Used by the DashboardController when a Client has no open Accounts.
 * @see DashboardController
 * @author Mathieu Bernardin
 */
public class BlankAccount extends Account{
    /**
     * Basic constructor for this class. Sets the accountID to a generic 'X'.
     */
    public BlankAccount() throws InvalidTypeException {
        super();
        accountID="z";
    }
    /**
     * Returns a representation of this class as a String.
     * @return a message to be displayed to the user, saying no accounts are open.
     */
    @Override
    public String toString() {
        return "No more accounts to show...";
    }
    /**
     * Adjusts the parent's method to do nothing.
     * @throws InsufficientFundsException always.
     */
    @Override
    public void withdraw(double amnt) throws InvestmentLockException, InsufficientFundsException {
        throw new InsufficientFundsException();
    }
    // TODO javadoc
    @Override
    public boolean deposit(double amnt) {
        return false;
    }
    // TODO javadoc
    @Override
    public boolean transfer(double amnt, String transferToID) throws InvestmentLockException, InsufficientFundsException, InvalidTypeException {
        return false;
    }
    // TODO javadoc
    @Override
    public boolean deposit(double amnt, String currencyCode) {
        return false;
    }
    /**
     * Implementation of the Maintainable interface. Functionally does nothing as this account requires no maintainance.
     */
    @Override
    public void maintain() {}
}

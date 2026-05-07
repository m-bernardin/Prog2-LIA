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
        accountID="X";
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
     * Implements the parent's method to do nothing.
     * @throws InsufficientFundsException always.
     */
    @Override
    public void withdraw(double amnt) throws InvestmentLockException, InsufficientFundsException {
        throw new InsufficientFundsException();
    }
}

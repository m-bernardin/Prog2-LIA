package com.example;

import java.time.LocalDate;
/**
 * A type of earnings account which cannot be withdrawn from until a year after being opened.
 * @author Mathieu Bernardin
 */
public class InvestmentAccount extends EarningsAccount{
    /**
     * The date this account was opened.
     */
    private LocalDate dateOpened;
    /**
     * Constructor for this class. Automatically generates an account ID, the interest for this account, and the date this account was opened.
     * @throws InvalidTypeException if something goes wrong.
     */
    public InvestmentAccount() throws InvalidTypeException {
        super();
        boolean validID=false;
        while(!validID){
            accountID=IdCreator.createID(2,3);
            if(!App.driver.exists(accountID))validID=true;
        }
        interestRate=0.05;
        dateOpened=LocalDate.now();
    }
    /**
     * Gets the date this account was opened.
     * @return this accounts opening date.
     */
    public LocalDate getDateOpened() {
        return dateOpened;
    }
    /**
     * Withdraws a specified amount from this account, but only if it has been open for more than a year.
     * @param amnt - the amount to be withdrawn.
     * @throws InsufficientFundsException if there are not enough funds for the withdrawal in this account.
     * @throws InvestmentLockException if this account has been open for less than a year.
     */
    @Override
    public void withdraw(double amnt) throws InvestmentLockException, InsufficientFundsException {
        if(dateOpened.plusYears(1).isBefore(LocalDate.now())){
            if(balance>=amnt){
                balance-=amnt;
                return;
            }
            throw new InsufficientFundsException();
        }
        throw new InvestmentLockException(LocalDate.now().until(dateOpened.plusYears(1)).toString());
    }
    /**
     * Gets a String representation of this account.
     * @return the representation of this account.
     */
    @Override
    public String toString() {
        return "Investment account no. "+accountID+"\tbalance: "+balance+"$";
    }
}
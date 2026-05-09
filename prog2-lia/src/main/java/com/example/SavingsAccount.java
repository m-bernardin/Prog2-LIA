package com.example;
/**
 * The basic type of earnings account. Essentialy a concrete implementation of super.
 * @author Mathieu Bernardin
 */
public class SavingsAccount extends EarningsAccount{
    /**
     * Constructor for this class. Automatically generates account ID (type (2,2)) and sets interest rate.
     * @throws InvalidTypeException if something goes wrong.
     */
    public SavingsAccount() throws InvalidTypeException {
        super();
        boolean validID=false;
        while(!validID){
            accountID=IdCreator.createID(2,2);
            if(!App.driver.exists(accountID))validID=true;
        }
        interestRate=0.02;
    }
    // TODO javadoc
    public SavingsAccount(double balance) throws InvalidTypeException {
        super(balance);
        accountID=IdCreator.createID(2,2);
        interestRate=0.02;
    }

    /**
     * Withdraws a specified amount from this account.
     * @param amnt - the amount to be withdrawn.
     * @throws InsufficientFundsException if there are insufficient funds for this withdrawal.
     */
    @Override
    public void withdraw(double amnt) throws InsufficientFundsException {
        if(balance>=amnt){
            balance-=amnt;
            return;
        }
        throw new InsufficientFundsException();
    }
    /**
     * Gets a representation of this account as a String.
     * @return the representation of this account.
     */
    @Override
    public String toString() {
        return "Savings account no. "+accountID+"\t balance: "+balance+"$";
    }
}
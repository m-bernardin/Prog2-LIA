package com.example;
/**
 * Represents a chequeing account. Essentially a concrete implementation of the parent.
 * @author Mathieu Bernardin
 */
public class ChequeingAccount extends Account{
    /**
     * Basic constrcutor for this class. IdCreator type (2,1).
     */
    public ChequeingAccount() throws InvalidTypeException {
        super();
        boolean validID=false;
        while(!validID){
            accountID=IdCreator.createID(2,1);
            if(!App.driver.exists(accountID))validID=true;
        }
    }
    /**
     * Withdraws a specified amount from this account.
     * @param amnt - the amount to be withdrawn.
     * @throws InsufficientFundsException if this account does not have sufficient funds for this withdrawal.
     */
    @Override
    public void withdraw(double amnt) throws InsufficientFundsException{
        if(balance>=amnt){
            balance-=amnt;
            return;
        }
        throw new InsufficientFundsException();
    }
    /**
     * Returns a reperesentation of this acccount as a String.
     * @return the representation.
     */
    @Override
    public String toString() {
        return "Chequeing account no. "+accountID+"\tbalance: "+balance+"$";
    }
}
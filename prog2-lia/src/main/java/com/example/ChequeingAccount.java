package com.example;

import java.time.LocalDate;
import java.time.Period;

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
        accountID=IdCreator.createSafeID(2,1);
    }
    /**
     * Constructor for this class which bypasses duplicate ID check. Otherwise identical to main constructor for this class.
     * @param balance - this account's starting balance.
     * @throws InvalidTypeException if something goes wrong.
     */
    public ChequeingAccount(double balance) throws InvalidTypeException {
        super(balance);
        accountID=IdCreator.createID(2,1);
    }
    /**
     * Withdraws a specified amount from this account.
     * @param amnt - the amount to be withdrawn.
     * @throws InsufficientFundsException if this account does not have sufficient funds for this withdrawal.
     */
    @Override
    public void withdraw(double amnt) throws InsufficientFundsException{
        if(balance>=amnt){
            if(rewardsProgramMember)setPoints(points+(amnt*2.5));
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
        return "Chequeing account no. "+accountID+"\t balance: "+balance+"$";
    }
    // TODO javadoc
    @Override
    public void withdraw(double amnt, boolean rewardable) throws InvestmentLockException, InsufficientFundsException {
        if(!rewardable){
            if(balance>=amnt){
                balance-=amnt;
                return;
            }
            throw new InsufficientFundsException();
        }
        withdraw(amnt);
    }
    /**
     * Implementation of the Maintainable interface. Defines what must be done each time the owner of this account logs in.
     * @return true if the maintenance is successful; false otherwise.
     */
    @Override
    public void maintain(){
        Period periodSinceLastOpened=Period.between(LocalDate.now(), getDateLastOpened());
        int monthsSinceLastOpened=periodSinceLastOpened.getMonths()+periodSinceLastOpened.getYears()*12;
        for(int i=0;i<monthsSinceLastOpened;++i){
            balance-=getMonthlyFees();
        }
        updateDateLastOpened();
    }
}
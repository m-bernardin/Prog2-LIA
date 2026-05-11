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
        accountID=IdCreator.createSafeID(2,2);
        interestRate=0.02;
    }
    /**
     * Constructor for this class which bypasses duplicate ID check. Otherwise identical to main constructor for this class.
     * @param balance - this account's starting balance.
     * @throws InvalidTypeException if something goes wrong.
     */
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
            if(rewardsProgramMember)setPoints(points+(amnt*2.5));
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
    /**
     * Withdraws a specified amount from this account, while allowing for specifying if points may be earned from this withdrawal .
     * @param amnt - the amount to be withdrawn.
     * @param rewardable - if points may be earned from this withdrawal.
     * @throws InsufficientFundsException if this account does not have sufficient funds for this withdrawal.
     */
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
}
package com.example;

import java.time.LocalDate;
import java.time.Period;

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
     * Constructor for this class which bypasses duplicate ID check. Otherwise identical to main constructor for this class.
     * @param balance - this account's starting balance.
     * @throws InvalidTypeException if something goes wrong.
     */
    public EarningsAccount(double balance) throws InvalidTypeException {
        super(balance);
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
    @Override
    public void applyInterest() {
        balance+=balance*(interestRate);
    }
    /**
     * Applies a specified amount of extra interest to this account.
     * @param interest - the extra interest to be applied.
     */
    public void addInterest(double interest){
        interestRate+=interest;
    }
    /**
     * Implementation of the Maintainable interface. Defines what must be done each time the owner of this account logs in.
     * @return true if the maintenance is successful; false otherwise.
     */
    @Override
    public void maintain(){
        LocalDate dateLastOpened=getDateLastOpened();
        if(dateLastOpened==null)return;
        updateDateLastOpened();
        Period periodSinceLastOpened=Period.between(LocalDate.now(),dateLastOpened);
        int monthsSinceLastOpened=periodSinceLastOpened.getMonths()+periodSinceLastOpened.getYears()*12;
        for(int i=0;i<monthsSinceLastOpened;++i){
            balance*=interestRate;
            balance-=getMonthlyFees();
        }
    }
}
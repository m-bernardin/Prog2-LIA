package com.example;

import java.time.LocalDate;
public class InvestmentAccount extends EarningsAccount{
    private LocalDate dateOpened;
    public InvestmentAccount() throws InvalidTypeException {
        super();
        accountID=IdCreator.createID(2,3);
        interestRate=0.05;
        dateOpened=LocalDate.now();
    }
    public LocalDate getDateOpened() {
        return dateOpened;
    }
    public void setDateOpened(LocalDate dateOpened) {
        this.dateOpened = dateOpened;
    }
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
    @Override
    public String toString() {
        return "Investment account no. "+accountID+"\tbalance: "+balance+"$";
    }
}
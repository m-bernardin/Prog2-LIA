package com.example;

import java.time.LocalDate;
public class InvestmentAccount extends EarningsAccount{
    private LocalDate dateOpened;
    public InvestmentAccount(double balance,LocalDate dateOpened, String accountID) {
        super(balance, 0.05, accountID);
        this.dateOpened = dateOpened;
    }
    public LocalDate getDateOpened() {
        return dateOpened;
    }
    public void setDateOpened(LocalDate dateOpened) {
        this.dateOpened = dateOpened;
    }
    @Override
    public boolean withdraw(double amnt) throws InvestmentLockException {
        if(dateOpened.plusYears(1).isBefore(LocalDate.now())){
            if(balance>=amnt){
                balance-=amnt;
                return true;
            }
            return false;
        }
        throw new InvestmentLockException(LocalDate.now().until(dateOpened.plusYears(1)).toString());
    }
}
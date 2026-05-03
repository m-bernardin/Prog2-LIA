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
    public void withdraw(double amnt) throws InvestmentLockException, InsufficientFundsException {
        if(dateOpened.plusYears(1).isBefore(LocalDate.now())){
            if(balance.get()>=amnt){
                balance.subtract(amnt);
                return;
            }
            throw new InsufficientFundsException();
        }
        throw new InvestmentLockException(LocalDate.now().until(dateOpened.plusYears(1)).toString());
    }
}
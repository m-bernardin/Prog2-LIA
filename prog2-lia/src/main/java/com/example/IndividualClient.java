package com.example;

import java.time.LocalDate;
public class IndividualClient extends StandardClient{
    public IndividualClient(String username, String password, String name, String contact) throws InvalidTypeException {
        super(username, password, name, contact);
        setClientID(IdCreator.createID(1,1));
    }
    @Override
    protected boolean maintain() {
        // TODO find failure (insufficient funds?)
        boolean sufficientFunds=applyMonthlyFee();
        setDateLastOpened(LocalDate.now());
        return sufficientFunds;
    }
    @Override
    public boolean applyMonthlyFee() {
        try {
            return App.driver.withdraw(calculateTotalMonthlyFee(),App.driver.getChequing(getClientID()));
        } catch (NullPointerException e) {
            return false;
        }
    }
}
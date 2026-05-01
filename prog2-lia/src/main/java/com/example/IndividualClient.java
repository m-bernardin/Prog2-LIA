package com.example;

import java.time.LocalDate;
public class IndividualClient extends StandardClient{
    public IndividualClient(String clientID, String username, String password, LocalDate dateLastOpened, LocalDate dateOpened,
            String name, String contact) {
        super(clientID, username, password, dateLastOpened, dateOpened, name, contact);
    }
    @Override
    protected boolean maintain() {
        // TODO Auto-generated method stub
        boolean success=true;
        applyMonthlyFee();
        return success;
    }
    @Override
    public boolean applyMonthlyFee() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyMonthlyFee'");
    }
}
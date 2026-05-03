package com.example;

import java.time.LocalDate;
public class StudentClient extends StandardClient{
    private LocalDate statusExpiryDate;
    private boolean statusValid;
    public StudentClient(String username, String password, String name, String contact) throws InvalidTypeException {
        super(username, password, name, contact);
        setClientID(IdCreator.createID(1,2));
        statusValid=false;
    }
    public LocalDate getStatusExpiryDate() {
        return statusExpiryDate;
    }
    public void setStatusExpiryDate(LocalDate statusExpiryDate) {
        this.statusExpiryDate = statusExpiryDate;
    }
    public boolean isStatusValid() {
        return statusValid;
    }
    public void setStatusValid(boolean statusValid) {
        this.statusValid = statusValid;
    }
    @Override
    public boolean maintain() throws InvalidTypeException {
        boolean sufficientFunds=super.maintain();
        if(LocalDate.now().isAfter(statusExpiryDate))statusValid=false;
        return sufficientFunds;
    }
}
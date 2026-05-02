package com.example;

import java.time.LocalDate;
public class StudentClient extends StandardClient{
    public StudentClient(String username, String password, String name, String contact) throws InvalidTypeException {
        super(username, password, name, contact);
        setClientID(IdCreator.createID(1,2));
        statusValid=false;
    }
    private LocalDate dateStatusRenewed;
    private boolean statusValid;
    @Override
    public boolean applyMonthlyFee() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyMonthlyFee'");
    }
    public LocalDate getDateStatusRenewed() {
        return dateStatusRenewed;
    }
    public void setDateStatusRenewed(LocalDate dateStatusRenewed) {
        this.dateStatusRenewed = dateStatusRenewed;
    }
    public boolean isStatusValid() {
        return statusValid;
    }
    public void setStatusValid(boolean statusValid) {
        this.statusValid = statusValid;
    }
    @Override
    protected boolean maintain() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'maintain'");
    }
}
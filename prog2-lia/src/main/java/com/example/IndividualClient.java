package com.example;
public class IndividualClient extends StandardClient{
    
    public IndividualClient(String username, String password, String name, String contact) throws InvalidTypeException {
        super(username, password, name, contact);
        setClientID(IdCreator.createID(1,1));
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
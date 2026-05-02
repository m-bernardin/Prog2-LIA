package com.example;

public class IndividualClient extends StandardClient{
    public IndividualClient(String username, String password, String name, String contact) throws InvalidTypeException {
        super(username, password, name, contact);
        setClientID(IdCreator.createID(1,1));
    }
}
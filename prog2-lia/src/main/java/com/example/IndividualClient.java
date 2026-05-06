package com.example;

public class IndividualClient extends StandardClient{
    public IndividualClient(String username, String password, String name, String contact) throws InvalidTypeException {
        super(username, password, name, contact);
        boolean validID=false;
        String ID="";
        while(!validID){
            ID=IdCreator.createID(1,1);
            if(!App.driver.exists(ID))validID=true;
        }
        setClientID(ID);
    }
}
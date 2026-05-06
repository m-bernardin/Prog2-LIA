package com.example;
/**
 * Represents the most basic kind of client. Essentialy a concrete implementation of the super account.
 */
public class IndividualClient extends StandardClient{
    /**
     * Constructor for this class. Automatically creates client ID.
     * @param username - the client's username.
     * @param password - the client's password.
     * @param name - the client's name.
     * @param contact - the client's contact.
     * @throws InvalidTypeException if something goes wrong.
     */
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
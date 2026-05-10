package com.example;

import java.time.LocalDate;
/**
 * A typoe of standard client belonging to a full time student. Account ownership fees are waived for them.
 * @author Mathieu Bernardin
 */
public class StudentClient extends StandardClient{
    /**
     * The date this client's full time student status expires.
     */
    private LocalDate statusExpiryDate;
    /**
     * If this client's full time student status if currently valid.
     */
    private boolean statusValid;
    /**
     * Constructor for this class. Automatically generates a client ID (type (1,2)).
     * @param username - this client's username.
     * @param password - this client's password.
     * @param name - this client's name.
     * @param contact - this client's contact.
     * @throws InvalidTypeException if something goes wrong.
     */
    public StudentClient(String username, String password, String name, String contact) throws InvalidTypeException {
        super(username, password, name, contact);
        setClientID(IdCreator.createSafeID(1,2));
        statusValid=false;
    }
    /**
     * Constructor for this class which bypasses duplicate ID check. Otherwise identical to main constructor for this class.
     * @param username - this client's username.
     * @param password - this client's password.
     * @param test - if this will be used for a test.
     * @param name - this client's name.
     * @param contact - this client's contact.
     * @throws InvalidTypeException if something goes wrong.
     */
    public StudentClient(String username, String password, boolean test, String name, String contact) throws InvalidTypeException {
        super(username, password, test, name, contact);
        String ID="";
        if(!test){
            boolean validID=false;
            while(!validID){
                ID=IdCreator.createID(1,2);
                if(!App.driver.exists(ID))validID=true;
            }
        }
        else ID=IdCreator.createID(1,2);
        setClientID(ID);
        statusValid=false;
    }
    /**
     * Gets the date this client's full time student status expires.
     * @return the date this client's full time student status expires.
     */
    public LocalDate getStatusExpiryDate() {
        return statusExpiryDate;
    }
    /**
     * Sets this client's full time student status to the specified date.
     * @param statusExpiryDate the new date of this client's full time student status.
     */
    public void setStatusExpiryDate(LocalDate statusExpiryDate) {
        this.statusExpiryDate = statusExpiryDate;
    }
    /**
     * If this client's full time student status is currently valid.
     * @return this client's full time student status.
     */
    public boolean isStatusValid() {
        return statusValid;
    }
    /**
     * Sets this client's full time student status to the specified quality.
     * @param statusValid the new quality of client's full time student status.
     */
    public void setStatusValid(boolean statusValid) {
        this.statusValid = statusValid;
    }
    /**
     * Verifies this client's full time student status is still valid, if its invalid, updates it accordingly.
     */
    // TODO reimplement
    // @Override
    // public boolean maintain() throws InvalidTypeException {
    //     if(LocalDate.now().isAfter(statusExpiryDate))statusValid=false;
    //     setDateLastOpened(LocalDate.now());
    //     return true;
    // }
}
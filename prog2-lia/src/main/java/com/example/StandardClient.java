package com.example;
/**
 * Represents a standard client.
 * @author Mathieu Bernardin
 */
public abstract class StandardClient extends Client{
    /**
     * This client's name.
     */
    private String name;
    /**
     * This client's contact.
     */
    private String contact;
    /**
     * Constrcutor for this class.
     * @param username - this client's username.
     * @param password - this client's password.
     * @param name - this client's name.
     * @param contact - this client's contact.
     */
    public StandardClient(String username, String password, String name, String contact) {
        super(username, password);
        this.name = name;
        this.contact = contact;
    }
    /**
     * Constructor for this class which bypasses duplicate ID check. Otherwise identical to main constructor for this class.
     * @param username - this client's username.
     * @param password - this client's password.
     * @param test - if this will be used for a test.
     * @param name - this client's name.
     * @param contact - this client's contact.
     */
    public StandardClient(String username, String password, boolean test, String name, String contact) {
        super(username, password, test);
        this.name = name;
        this.contact = contact;
    }
    /**
     * Gets this client's name.
     * @return this client's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets this client's contact.
     * @return this client's contact.
     */
    public String getContact() {
        return contact;
    }
}
package com.example;

public abstract class StandardClient extends Client{
    private String name;
    private String contact;
    public StandardClient(String username, String password, String name, String contact) {
        super(username, password);
        this.name = name;
        this.contact = contact;
    }
    // public StandardClient(String clientID, String username, String password, LocalDate dateLastOpened, LocalDate dateOpened,
    //         String name, String contact) {
    //     super(clientID, username, password, dateLastOpened, dateOpened);
    //     this.name = name;
    //     this.contact = contact;
    // }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
}
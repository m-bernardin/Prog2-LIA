package com.example;

import java.io.*;
import java.util.*;
import com.google.gson.*;
public class Driver{
    public static void main(String[] args){
        Driver runner=new Driver();
        System.out.println("Running application...");
        try {
            runner.run();
        } catch (MissingFileException e) {
            System.out.println("Fatal error occured: "+e.getMessage());
        }
        System.out.println("Application close... Goodbye :)");
    }
    private ArrayList<Client> clients;
    private Client activeClient;
    private ArrayList<Account> accounts;
    private ArrayList<Account> activeAccounts;
    private ArrayList<Transaction> transactions;
    public Client getActiveClient() {
        return activeClient;
    }
    public void setActiveClient(Client activeClient) {
        this.activeClient = activeClient;
    }
    public void run() throws MissingFileException{
        // TODO complete method
        loadData();
        boolean running=true;
        Scanner input=new Scanner(System.in);
        login();
        while(running){
            System.out.print("Please select an option\n1. \n> ");
            switch (input.nextInt()) {
                case 1:
                    
                    break;
                default:
                    System.out.println("Please select a valid option...");
                    break;
            }
        }
    }
    public void loadData() throws MissingFileException {
        // TODO verify this works
        loadClients();
        loadAccounts();
        loadTransactions();
    }
    private void login() {
        // TODO verify this works
        boolean loggingIn=true;
        Scanner input=new Scanner(System.in);
        while(loggingIn){
            System.out.print("Please enter your username\n> ");
            String username=input.next();
            System.out.print("Please enter your passsword\n> ");
            String password=input.next();
            Client client=verifyCredentials(username,password);
            if(client!=null){
                setActiveClient(client);
                for(String clientAccountID:getActiveClient().getAccounts()){
                    for(Account loadedAccount:accounts){
                        if(loadedAccount.getAccountID().equals(clientAccountID)){
                            activeAccounts.add(loadedAccount);
                        }
                    }
                }
                System.out.println("Logged in!");
            }
            else System.out.println("Username or password incorrect... Please try again");
        }
    }
    public boolean login(String username,String password){
        Client client=verifyCredentials(username,password);
        if(client!=null){
            setActiveClient(client);
            for(String clientAccountID:getActiveClient().getAccounts()){
                for(Account loadedAccount:accounts){
                    if(loadedAccount.getAccountID().equals(clientAccountID)){
                        activeAccounts.add(loadedAccount);
                    }
                }
            }
            return true;
        }
        return false;
    }
    private Client verifyCredentials(String username, String password) {
        for(Client client:clients){
            if(client.getUsername().equals(username)&&client.getPassword().equals(password))return client;
        }
        return null;
    }
    private void loadClients() throws MissingFileException{
        File here=new File("."); // **
        System.out.println("**execution path (absolute): "+here.getAbsolutePath());
        File clientJsons=new File("src/main/resources/com/example/json/clients.json");
        FileReader fileReader;
        try {
            fileReader=new FileReader(clientJsons);
        } catch (FileNotFoundException e) {
            throw new MissingFileException("Could not find client data file...");
        }
        Gson clientGsons=new Gson();
        JsonArray clientJsonArray;
        clientJsonArray=clientGsons.fromJson(fileReader,JsonArray.class);
        for (JsonElement jsonElement : clientJsonArray) {
            JsonObject client=jsonElement.getAsJsonObject();
            String clientID=new Gson().fromJson(client.get("clientID"),String.class);
            switch (clientID.charAt(0)) {
                case 'b':
                    clients.add(new Gson().fromJson(client, IndividualClient.class));
                    break;
                case 'c':
                    clients.add(new Gson().fromJson(client, StudentClient.class));
                    break;
                case 'd':
                    clients.add(new Gson().fromJson(client, CorporateClient.class));
                    break;
                case 'e':
                    clients.add(new Gson().fromJson(client, VipClient.class));
                    break;
                default:
                    System.out.println("## could not find type...");
                    break;
            }
        }
    }
    private void loadAccounts() throws MissingFileException {
        File here=new File("."); // **
        System.out.println("**execution path (absolute): "+here.getAbsolutePath());
        File clientJsons=new File("src/main/resources/com/example/json/acounts.json");
        FileReader fileReader;
        try {
            fileReader=new FileReader(clientJsons);
        } catch (FileNotFoundException e) {
            throw new MissingFileException("Could not find account data file...");
        }
        Gson accountGsons=new Gson();
        JsonArray accountJsonArray;
        accountJsonArray=accountGsons.fromJson(fileReader,JsonArray.class);
        for (JsonElement jsonElement : accountJsonArray) {
            JsonObject account=jsonElement.getAsJsonObject();
            String accountID=new Gson().fromJson(account.get("accountID"),String.class);
            switch (accountID.charAt(0)) {
                case 'l':
                    accounts.add(new Gson().fromJson(account, ChequingAccount.class));
                    break;
                case 'm':
                    accounts.add(new Gson().fromJson(account, SavingsAccount.class));
                    break;
                case 'n':
                    accounts.add(new Gson().fromJson(account, InvestmentAccount.class));
                    break;
                default:
                    System.out.println("## could not find type...");
                    break;
            }
        }
    }
    private void loadTransactions() throws MissingFileException {
        File here=new File("."); // **
        System.out.println("**execution path (absolute): "+here.getAbsolutePath());
        File transactionJsons=new File("src/main/resources/com/example/json/transactions.json");
        FileReader fileReader;
        try {
            fileReader=new FileReader(transactionJsons);
        } catch (FileNotFoundException e) {
            throw new MissingFileException("Could not find transaction data file...");
        }
        Gson transactionGsons=new Gson();
        JsonArray transactionJsonArray;
        transactionJsonArray=transactionGsons.fromJson(fileReader,JsonArray.class);
        for (JsonElement jsonElement : transactionJsonArray) {
            JsonObject transaction=jsonElement.getAsJsonObject();
            transactions.add(new Gson().fromJson(transaction, Transaction.class));
        }
    }
}
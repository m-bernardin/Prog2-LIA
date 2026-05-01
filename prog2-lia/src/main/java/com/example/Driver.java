package com.example;

import java.io.*;
import java.time.LocalDate;
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
    public ArrayList<Client> getClients() {
        // TODO remove after testing
        return clients;
    }
    private String activeClient;
    private ArrayList<Account> accounts;
    private ArrayList<String> activeAccounts;
    private ArrayList<Transaction> transactions;
    public String getActiveClient() {
        return activeClient;
    }
    public void setActiveClient(String activeClient) {
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
    public void saveData() throws IOException{
        saveClients();
        saveAccounts();
        saveTransactions();
    }
    private void saveClients() throws IOException {
        File clientJsons=new File("src/main/resources/com/example/json/clients.json");
        clientJsons.delete();
        clientJsons.createNewFile();
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        JsonArray clientJsonArray=new JsonArray();
        FileWriter writer=new FileWriter(clientJsons);
        for (Client client : clients) {
            String clientJsonString=gson.toJson(client);
            JsonElement clientJson=gson.fromJson(clientJsonString, JsonElement.class);
            clientJsonArray.add(clientJson);
        }
        String clientJsonArrayString=gson.toJson(clientJsonArray);
        writer.append(clientJsonArrayString);
        writer.flush();
        writer.close();
    }
    private void saveAccounts() throws IOException {
        File accountJsons=new File("src/main/resources/com/example/json/accounts.json");
        accountJsons.delete();
        accountJsons.createNewFile();
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        JsonArray accountJsonArray=new JsonArray();
        FileWriter writer=new FileWriter(accountJsons);
        for (Account account : accounts) {
            String accountJsonString=gson.toJson(account);
            JsonElement accountJson=gson.fromJson(accountJsonString, JsonElement.class);
            accountJsonArray.add(accountJson);
        }
        String accountJsonArrayString=gson.toJson(accountJsonArray);
        writer.append(accountJsonArrayString);
        writer.flush();
        writer.close();
    }
    private void saveTransactions() throws IOException {
        File transactionJsons=new File("src/main/resources/com/example/json/transactions.json");
        transactionJsons.delete();
        transactionJsons.createNewFile();
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        JsonArray transactionJsonArray=new JsonArray();
        FileWriter writer=new FileWriter(transactionJsons);
        for (Transaction transaction : transactions) {
            String transactionJsonString=gson.toJson(transaction);
            JsonElement transactionJson=gson.fromJson(transactionJsonString, JsonElement.class);
            transactionJsonArray.add(transactionJson);
        }
        String transactionJsonArrayString=gson.toJson(transactionJsonArray);
        writer.append(transactionJsonArrayString);
        writer.flush();
        writer.close();
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
                setActiveClient(client.getClientID());
                for(String clientAccountID:client.getAccounts()){
                    for(Account loadedAccount:accounts){
                        if(loadedAccount.getAccountID().equals(clientAccountID)){
                            activeAccounts.add(loadedAccount.getAccountID());
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
            setActiveClient(client.getClientID());
            for(String clientAccountID:client.getAccounts()){
                for(Account loadedAccount:accounts){
                    if(loadedAccount.getAccountID().equals(clientAccountID)){
                        activeAccounts.add(loadedAccount.getAccountID());
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
        clients=new ArrayList<>();
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
        accounts=new ArrayList<>();
        File here=new File("."); // **
        System.out.println("**execution path (absolute): "+here.getAbsolutePath());
        File clientJsons=new File("src/main/resources/com/example/json/accounts.json");
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
        transactions=new ArrayList<>();
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
    public void createClient(String username,String password,int type) throws InvalidTypeException{
        // TODO find way to take unique things for each type (split???)
        boolean success=true;
        LocalDate dateOpened=LocalDate.now();
        String clientID=IdCreator.createID(1, type);
        Client newClient;
        switch (type) {
            case 1:
                newClient=new IndividualClient(clientID, username, password, null, dateOpened, null, null);
                break;
            case 2:
                newClient=new StudentClient(clientID, username, password, null, dateOpened, null, null, null, true);
                break;
            case 3:
                newClient=new CorporateClient(clientID, username, password, null, dateOpened, false, null, null);
                break;
            case 4:
                newClient=new VipClient(clientID, username, password, null, dateOpened, false, null, null);
                break;
            default:
                throw new InvalidTypeException();
        }
        clients.add(newClient);
    }
}
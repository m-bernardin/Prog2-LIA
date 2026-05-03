package com.example;

import java.io.*;
import java.util.*;
import com.google.gson.*;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
public class Driver{
    @Deprecated
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
    private String activeClient;
    private ArrayList<Account> accounts;
    private HashSet<String> activeAccounts;
    private ArrayList<Transaction> transactions;
    public FilteredList<Transaction> latestTransactions;
    private ObservableList<Account> observableAccounts;
    public FilteredList<Account> observableActiveAccounts;
    public final ObjectProperty<FilteredList<Account>> observableActiveAccountsProperty=new SimpleObjectProperty<>();
    public ObjectProperty<FilteredList<Account>> getObservableActiveAccountsProperty() {
        return observableActiveAccountsProperty;
    }
    public ArrayList<Account> getAccounts() {
        return accounts;
    }
    public String getActiveClient() {
        return activeClient;
    }
    public void setActiveClient(String activeClient) {
        this.activeClient = activeClient;
    }
    @Deprecated
    public void run() throws MissingFileException{
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
        input.close();
    }
    public void loadData() throws MissingFileException {
        loadClients();
        loadAccounts();
        loadTransactions();
    }
    public void saveData() throws IOException{
        saveClients();
        saveAccounts();
        saveTransactions();
    }
    public boolean login(String username,String password){
        Client client=verifyCredentials(username,password);
        activeAccounts=new HashSet<>();
        if(client!=null){
            setActiveClient(client.getClientID());
            for(String clientAccountID:client.getAccounts()){
                for(Account loadedAccount:accounts){
                    if(loadedAccount.getAccountID().equals(clientAccountID)){
                        activeAccounts.add(loadedAccount.getAccountID());
                    }
                }
            }
            observableAccounts=FXCollections.observableArrayList(accounts);
            observableActiveAccounts=new FilteredList<>(observableAccounts, account -> activeAccounts.contains(account.getAccountID()));
            observableActiveAccountsProperty.set(observableActiveAccounts);
            return true;
        }
        return false;
    }
    public void logout(){
        activeAccounts=null;
        activeClient=null;
        observableAccounts=null;
        observableActiveAccounts=null;

    }
    public void createIndividualClient(String username,String password,String name,String contact) throws InvalidTypeException{
        IndividualClient client=new IndividualClient(username, password, name, contact);
        clients.add(client);
    }
    public void createStudentClient(String username,String password,String name,String contact) throws InvalidTypeException{
        StudentClient client=new StudentClient(username, password, name, contact);
        clients.add(client);
    }
    public void createCorporateClient(String username,String password,String companyName,ArrayList<String> clientManagerContacts,boolean rewardsProgramMember) throws InvalidTypeException{
        CorporateClient client=new CorporateClient(username, password, rewardsProgramMember, companyName, clientManagerContacts);
        clients.add(client);
    }
    public void createVipClient(String username,String password,boolean rewardsProgramMember,String name,String contact) throws InvalidTypeException{
        VipClient client=new VipClient(username, password, rewardsProgramMember, name, contact);
        clients.add(client);
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
    @Deprecated
    private void login() {
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
        input.close();
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
                    accounts.add(new Gson().fromJson(account, ChequeingAccount.class));
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
    public boolean deposit(double amnt,String depositToId){
        return getAccount(depositToId).deposit(amnt);
    }
    public boolean isPremium(String clientID){
        Client client=null;
        if(clientID==null)return false;
        for (Client search : clients) {
            if(search.getClientID().equals(clientID)){
                client=search;
                break;
            }
        }
        if(client==null)return false;
        if(client.getClass()==VipClient.class||client.getClass()==CorporateClient.class)return true;
        return false;
    }
    public String getOwner(String accountID) throws NullPointerException{
        for (Client client : clients) {
            ArrayList<String> ownedAccounts=client.getAccounts();
            for (String ownedAccount : ownedAccounts) {
                if(ownedAccount.equals(accountID))return client.getClientID();
            }
        }
        throw new NullPointerException();
    }
    public void withdraw(double amnt,String accountID) throws InvestmentLockException, InsufficientFundsException, NullPointerException{
        if(accountID==null)throw new NullPointerException();
        for (Account account : accounts) {
            if(account.getAccountID().equals(accountID)){
                account.withdraw(amnt);
            }
        }
    }
    public String getChequing(String clientID) throws NullPointerException{
        for (String accountID : getClient(clientID).getAccounts()) {
            if(getAccount(accountID).getClass()==ChequeingAccount.class)return accountID;
        }
        throw new NullPointerException();
    }
    public Account getAccount(String accountID) throws NullPointerException{
        for (Account account : accounts) {
            if(account.getAccountID().equals(accountID))return account;
        }
        throw new NullPointerException();
    }
    public Client getClient(String clientID) throws NullPointerException{
        for (Client client : clients) {
            if(client.getClientID().equals(clientID))return client;
        }
        throw new NullPointerException();
    }
    public ArrayList<String> getOwnedEarningsAccounts(String clientID){
        ArrayList<String> ownedAccounts=new ArrayList<>();
        for (String accountID : getClient(clientID).getAccounts()) {
            if(getAccount(accountID).getClass()==InvestmentAccount.class||getAccount(accountID).getClass()==SavingsAccount.class)ownedAccounts.add(accountID);
        }
        return ownedAccounts;
    }
    public EarningsAccount getEarningsAccount(String accountID) throws NullPointerException{
        if(getAccount(accountID).getClass()==InvestmentAccount.class||getAccount(accountID).getClass()==SavingsAccount.class)return (EarningsAccount)getAccount(accountID);
        throw new NullPointerException();
    }
    public boolean transfer(String donner,String recipient,double amnt) throws NullPointerException, InvestmentLockException, InsufficientFundsException{
        return getAccount(donner).transfer(amnt, recipient);
    }
    public void openChequeing() throws InvalidTypeException{
        String accountID=IdCreator.createID(2,1);
        Account newAccount=new ChequeingAccount(0, accountID);
        getClient(activeClient).addAccount(accountID);
        accounts.add(newAccount);
    }
}
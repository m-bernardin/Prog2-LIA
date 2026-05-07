package com.example;

import java.io.*;
import java.util.*;
import com.google.gson.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
/**
 * The main manager for objects in the app. Allows clients to interact with accounts and controllers to interact with the app's data.
 * @author Mathieu Bernardin
 */
public class Driver{
    /**
     * List of all loaded clients.
     */
    private ArrayList<Client> clients;
    /**
     * ID of the currently logged in client;
     */
    private String activeClient;
    /**
     * List of all loaded accounts. Observable by controllers.
     */
    private final ObservableList<Account> accounts=FXCollections.observableArrayList();
    /**
     * List of loaded accounts, filtered to accounts owned by the currently logged in client. Observing accounts and observable by controllers.
     */
    public final FilteredList<Account> filteredAccounts=new FilteredList<>(accounts);
    /**
     * List of all loaded transactions. Observable by controllers.
     */
    private final ObservableList<Transaction> transactions=FXCollections.observableArrayList();
    /**
     * List of all transactions, filtered to only those associated with the selected account. Observing transactions and observable.
     */
    private final FilteredList<Transaction> filteredTransactions=new FilteredList<>(transactions);
    /**
     * List of all filtered transactions, sorted by date and time. Observing filtertedTransactions and observable.
     */
    private final SortedList<Transaction> sortedTransactions=new SortedList<>(filteredTransactions);
    /**
     * List of the ten latest transactions associated with the currently selected account. Observing sortedTransactions and observable.
     */
    private final FilteredList<Transaction> latestTransactions=new FilteredList<>(sortedTransactions);
    /**
     * Property holding the ID of the currently selected account. Bound by the dashboard controller to the selection of accountView.
     */
    public final StringProperty selectedAccount=new SimpleStringProperty();
    /**
     * Gets the ten latest transactions associated with the currently selected account.
     * @return the ten latest transactions associated with the currently selected account.
     */
    public ObservableList<Transaction> getLatestTransactions() {
        return latestTransactions;
    }
    /**
     * Gets the list of loaded accounts.
     * @return the list of loaded accounts.
     */
    public ObservableList<Account> getAccounts() {
        return accounts;
    }
    /**
     * Gets the ID of the currently logged in client.
     * @return the ID of the currently logged in client.
     */
    public String getActiveClient() {
        return activeClient;
    }
    /**
     * Sets the active client to that of a specified client ID.
     * @param activeClient - the ID to set the active client to.
     */
    public void setActiveClient(String activeClient) {
        this.activeClient = activeClient;
    }
    /**
     * Loads all data from json into memory.
     * @throws MissingFileException if a json file has been deleted or is unreadable.
     */
    public void loadData() throws MissingFileException {
        clients=new ArrayList<>();
        loadClients();
        loadAccounts();
        loadTransactions();
    }
    /**
     * Saves all data in memory into json.
     * @throws IOException if something goes wrong.
     */
    public void saveData() throws IOException{
        saveClients();
        saveAccounts();
        saveTransactions();
    }
    /**
     * Attempts to log a client in, with a provided username and password.
     * @param username - the username of the client to be logged in.
     * @param password - the password of the client to be logged in.
     * @return true if successful; false otherwise.
     */
    public boolean login(String username,String password){
        Client client=verifyCredentials(username,password);
        if(client!=null){
            setActiveClient(client.getClientID());
            refreshAccounts();
            selectedAccount.addListener((obs,oldAccount,newAccount)->{
                if(newAccount==null||newAccount.isBlank()){
                    filteredTransactions.setPredicate(transaction->true);
                    return;
                }
                filteredTransactions.setPredicate(transaction->transaction.isAssociatedTo(newAccount));
            });
            sortedTransactions.setComparator(Comparator.comparing(Transaction::getDateTime).reversed());
            refreshLatest();
            transactions.addListener((ListChangeListener<Transaction>)transaction->refreshLatest());
            transactions.addListener((ListChangeListener<Transaction>)transaction->refreshAccounts());
            return true;
        }
        return false;
    }
    /**
     * Refreshes filteredAccounts when a new transactions is added.
     */
    private void refreshAccounts() {
        filteredAccounts.setPredicate(account -> getClient(activeClient).getAccounts().contains(account.getAccountID()));
    }
    /**
     * Refreshes latestTransactions when a new transactions is added.
     */
    private void refreshLatest() {
        latestTransactions.setPredicate(transaction->sortedTransactions.indexOf(transaction)<10);
    }
    /**
     * Logs out the current user.
     */
    public void logout(){
        activeClient=null;
    }
    /**
     * Allows the new client controller to create individual clients.
     * @param username - the client's username.
     * @param password - the client's passsword.
     * @param name - the client's name.
     * @param contact - the client's contact.
     * @throws InvalidTypeException if something goes wrong.
     */
    public void createIndividualClient(String username,String password,String name,String contact) throws InvalidTypeException{
        IndividualClient client=new IndividualClient(username, password, name, contact);
        clients.add(client);
    }
    /**
     * Allows the new client controller to create student clients.
     * @param username - the client's username.
     * @param password - the client's passsword.
     * @param name - the client's name.
     * @param contact - the client's contact.
     * @throws InvalidTypeException if something goes wrong.
     */
    public void createStudentClient(String username,String password,String name,String contact) throws InvalidTypeException{
        StudentClient client=new StudentClient(username, password, name, contact);
        clients.add(client);
    }
    /**
     * Allows the new client controller to create corporate clients.
     * @param username - the client's username.
     * @param password - the client's passsword.
     * @param companyName - the client's name.
     * @param clientManagerContacts - the list of finance managers for this client.
     * @param rewardsProgramMember - if this client is part of the rewards program.
     * @throws InvalidTypeException if something goes wrong.
     */
    public void createCorporateClient(String username,String password,String companyName,ArrayList<String> clientManagerContacts,boolean rewardsProgramMember) throws InvalidTypeException{
        CorporateClient client=new CorporateClient(username, password, rewardsProgramMember, companyName, clientManagerContacts);
        clients.add(client);
    }
    /**
     * Allows the new client controller to create student clients.
     * @param username - the client's username.
     * @param password - the client's passsword
     * @param rewardsProgramMember - if this client is part of the rewards program.
     * @param name - the client's name.
     * @param contact - the client's contact.
     * @throws InvalidTypeException if something goes wrong.
     */
    public void createVipClient(String username,String password,boolean rewardsProgramMember,String name,String contact) throws InvalidTypeException{
        VipClient client=new VipClient(username, password, rewardsProgramMember, name, contact);
        clients.add(client);
    }
    /**
     * Allows controllers and clients to deposit into accounts.
     * @param amnt - the amount to be deposited.
     * @param depositToId - the ID of the account to be deposited to.
     * @return true if successful; false otherwise.
     * @throws InvalidTypeException if something goes wrong.
     */
    public boolean deposit(double amnt,String depositToId) throws InvalidTypeException{
        boolean success=getAccount(depositToId).deposit(amnt);
        if(success)recordTransaction(null, depositToId, amnt);
        return success;
    }
    /**
     * Finds out if a given client is a premium client.
     * @param clientID - the ID of the client to be checked.
     * @return true if it is premium; false otherwise.
     */
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
    /**
     * Gets the ID of the owner of an account.
     * @param accountID - the ID of the account for which the owner will be found.
     * @return the ID of the owner.
     * @throws NullPointerException if no owner is found.
     */
    public String getOwner(String accountID) throws NullPointerException{
        for (Client client : clients) {
            ArrayList<String> ownedAccounts=client.getAccounts();
            for (String ownedAccount : ownedAccounts) {
                if(ownedAccount.equals(accountID))return client.getClientID();
            }
        }
        throw new NullPointerException();
    }
    /**
     * Allows controllers and clients to make withdrawals from accounts.
     * @param amnt - the amount to be withdrawn.
     * @param accountID - the ID of the account from which the withdrawal should be made.
     * @throws InvestmentLockException if a withdrawal is attempted from an investment account which has been open for less than a year.
     * @throws InsufficientFundsException if a withdrawal is attempted from an account with insufficient funds for the withdrawal.
     * @throws NullPointerException if the specified account does not exist.
     * @throws InvalidTypeException if something goes wrong.
     */
    public void withdraw(double amnt,String accountID) throws InvestmentLockException, InsufficientFundsException, NullPointerException, InvalidTypeException{
        if(accountID==null)throw new NullPointerException();
        for (Account account : accounts) {
            if(account.getAccountID().equals(accountID)){
                account.withdraw(amnt);
            }
        }
        recordTransaction(accountID,null, amnt);
    }
    /**
     * Gets a chequeing account owned by the specified client.
     * @param clientID - the client's ID.
     * @return the ID of the found account.
     * @throws NullPointerException if the client does not exist or they do not have chequeing account open. 
     */
    public String getChequing(String clientID) throws NullPointerException{
        for (String accountID : getClient(clientID).getAccounts()) {
            if(getAccount(accountID).getClass()==ChequeingAccount.class)return accountID;
        }
        throw new NullPointerException();
    }
    /**
     * Gets the account associated with the specified ID.
     * @param accountID - the ID of the account to be found.
     * @return the account found.
     * @throws NullPointerException if the account does not exist.
     */
    public Account getAccount(String accountID) throws NullPointerException{
        for (Account account : accounts) {
            if(account.getAccountID().equals(accountID))return account;
        }
        throw new NullPointerException();
    }
    /**
     * Gets the client associated with specified ID.
     * @param clientID - the ID of the client to be found.
     * @return the client found.
     * @throws NullPointerException if the client does not exist.
     */
    public Client getClient(String clientID) throws NullPointerException{
        for (Client client : clients) {
            if(client.getClientID().equals(clientID))return client;
        }
        throw new NullPointerException();
    }
    /**
     * Gets the IDs of all earnings accounts owned by the specified client.
     * @param clientID - the ID of the client.
     * @return a list of the IDs.
     * @throws NullPointerException if the client does not exist.
     */
    public ArrayList<String> getOwnedEarningsAccounts(String clientID) throws NullPointerException{
        ArrayList<String> ownedAccounts=new ArrayList<>();
        ArrayList<String> clientAccounts=getClient(clientID).getAccounts();
        for (String accountID : clientAccounts) {
            if(getAccount(accountID).getClass()==InvestmentAccount.class||getAccount(accountID).getClass()==SavingsAccount.class)ownedAccounts.add(accountID);
        }
        return ownedAccounts;
    }
    /**
     * Gets the specified account as an earnings account.
     * @param accountID - the ID of the account.
     * @return the account as an earnings acccount.
     * @throws NullPointerException if the account does not exist or is not an earnings account.
     */
    public EarningsAccount getEarningsAccount(String accountID) throws NullPointerException{
        if(getAccount(accountID).getClass()==InvestmentAccount.class||getAccount(accountID).getClass()==SavingsAccount.class)return (EarningsAccount)getAccount(accountID);
        throw new NullPointerException();
    }
    /**
     * Allows for controllers to make transfers.
     * @param donner - the ID of the account making the transfer.
     * @param recipient - the ID of the account recieving the transfer.
     * @param amnt - the amount to be transfered.
     * @return true if successfull; false otherwise.
     * @throws NullPointerException - if one or both of the accounts do not exits.
     * @throws InvestmentLockException - if the transfer is from an investment account opened less than a year ago.
     * @throws InsufficientFundsException - if the transfering account does not have enough funds for the transfer.
     * @throws InvalidTypeException - if something goes wrong.
     */
    public boolean transfer(String donner,String recipient,double amnt) throws NullPointerException, InvestmentLockException, InsufficientFundsException, InvalidTypeException{
        boolean success=getAccount(donner).transfer(amnt, recipient);
        if(success)recordTransaction(donner, recipient, amnt);
        return success;
    }
    /**
     * Allows controllers to open a chequeing account for the currently logged in client.
     * @throws InvalidTypeException if something goes wrong.
     */
    public void openChequeing() throws InvalidTypeException{
        Account newAccount=new ChequeingAccount();
        getClient(activeClient).addAccount(newAccount.getAccountID());
        accounts.add(newAccount);
    }
    /**
     * Allows controllers to open a savings account for the currently logged in client.
     * @throws InvalidTypeException if something goes wrong.
     */
    public void openSavings() throws InvalidTypeException {
        Account newAccount=new SavingsAccount();
        getClient(activeClient).addAccount(newAccount.getAccountID());
        accounts.add(newAccount);
    }
    /**
     * Allows controllers to open an investment account for the currently logged in client.
     * @throws InvalidTypeException if something goes wrong.
     */
    public void openInvestment() throws InvalidTypeException {
        Account newAccount=new InvestmentAccount();
        getClient(activeClient).addAccount(newAccount.getAccountID());
        accounts.add(newAccount);
    }
    public boolean exists(String ID){
        for(Transaction transaction:transactions){
            if(transaction.getTransactionID().equals(ID))return true;
        }
        for (Account account:accounts){
            if(account.getAccountID().equals(ID))return true;
        }
        for(Client client:clients){
            if(client.getClientID().equals(ID))return true;
        }
        return false;
    }
    /**
     * Saves client data in memory to json.
     * @throws IOException if something goes wrong.
     */
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
    /**
     * Saves account data in memory to json.
     * @throws IOException if something goes wrong.
     */
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
    /**
     * Saves transaction data in memory to json.
     * @throws IOException if something goes wrong.
     */
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
    /**
     * Verifies provided credentials with all loaded clients.
     * @param username - the username to be verified.
     * @param password - the password to be verified.
     * @return the client with the provided credentials; null if none found.
     */
    private Client verifyCredentials(String username, String password) {
        for(Client client:clients){
            if(client.getUsername().equals(username)&&client.getPassword().equals(password))return client;
        }
        return null;
    }
    /**
     * Loads all client data from json.
     * @throws MissingFileException if the file is missing or otherwise unreachable.
     */
    private void loadClients() throws MissingFileException{
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
    /**
     * Loads all account data from json.
     * @throws MissingFileException if the file is missing or otherwise unreachable.
     */
    private void loadAccounts() throws MissingFileException {
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
                case 'X':
                    accounts.add(new Gson().fromJson(account, BlankAccount.class));
                default:
                    System.out.println("## could not find type...");
                    break;
            }
        }
    }
    /**
     * Loads all transactions data from json.
     * @throws MissingFileException if the file is missing or otherwise unreachable.
     */
    private void loadTransactions() throws MissingFileException {
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
    /**
     * Records a transaction.
     * @param donner - the ID of the account making the transaction.
     * @param recipient - the ID of the account recieving the transaction.
     * @param amnt - the amount involved in the transaction.
     * @throws InvalidTypeException if something goes wrong.
     */
    private void recordTransaction(String donner,String recipient,double amnt) throws InvalidTypeException{
        Transaction transaction=new Transaction(amnt, recipient, donner);
        transactions.add(transaction);
    }
}
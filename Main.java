import java.util.*;
public class Main{
    private ArrayList<Client> clients;
    private Client activeClient;
    public Client getActiveClient() {
        return activeClient;
    }
    public void setActiveClient(Client activeClient) {
        this.activeClient = activeClient;
    }
    private ArrayList<Account> accounts;
    private ArrayList<Account> activeAccounts;
    private ArrayList<Transaction> transactions;
    public static void main(String[] args){
        Main runner=new Main();
        System.out.println("Running application...");
        runner.run();
        System.out.println("Application close... Goodbye :)");
    }
    public void run(){
        // TODO complete method
        loadClients();
        loadAccounts();
        loadTransactions();
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
    private void login() {
        // TODO Auto-generated method stub
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
    private Client verifyCredentials(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verifyCredentials'");
    }
    private void loadClients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadClients'");
    }
    private void loadAccounts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadAccounts'");
    }
    private void loadTransactions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadTransactions'");
    }
}
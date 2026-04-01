public abstract class Account {
    // fields
    protected double balance;
    private String accountID;
    // constructors
    public Account(double balance, String accountID) {
        this.balance = balance;
        this.accountID = accountID;
    }
    // getters and setters
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getAccountID() {
        return accountID;
    }
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }
    // abstract methods
    public abstract boolean withdraw(double amnt);
    // concrete methods
    public boolean deposit(double amnt){
        // TODO complete method
        throw new UnsupportedOperationException();
    }
    public boolean deposit(double amnt,String currency){
        // TODO complete method
        throw new UnsupportedOperationException();
    }
    public boolean transfer(double amnt,String transferToID){
        // TODO complete method
        throw new UnsupportedOperationException();
    }
}
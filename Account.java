public abstract class Account {
    // fields
    protected double balance;
    // constructors
    public Account(double balance) {
        this.balance = balance;
    }
    // getters and setters
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
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
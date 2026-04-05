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
    public abstract boolean withdraw(double amnt) throws InvestmentLockException;
    // concrete methods
    public boolean deposit(double amnt){
        // TODO find failure instance???
        boolean success=true;
        balance+=amnt;
        return success;
    }
    public boolean deposit(double amnt,String currency){
        // TODO add failure instance from regular deposit
        // TODO add logic for checking if from a premium acc
        boolean success=true;
        try {
            balance+=convert(amnt, currency);
        } catch (InvalidTypeException e) {
            success=false;
        }
        return success;
    }
    public boolean transfer(double amnt,String transferToID){
        // TODO complete method
        throw new UnsupportedOperationException();
    }
    // Data pulled from https://www.bankofcanada.ca/rates/exchange/annual-average-exchange-rates/; averages for 2025
    double convert(double amnt,String currencyCode) throws InvalidTypeException{
        if(currencyCode.equals("USD"))return amnt*1.3978;
        if(currencyCode.equals("GBP"))return amnt*1.8420;
        if(currencyCode.equals("JPY"))return amnt*0.009350;
        if(currencyCode.equals("EUR"))return amnt*1.5782;
        if(currencyCode.equals("AUD"))return amnt*0.9009;
        if(currencyCode.equals("CHF"))return amnt*1.6846;
        throw new InvalidTypeException();
    }
}
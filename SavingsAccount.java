public class SavingsAccount extends EarningsAccount{
    public SavingsAccount(double balance) {
        super(balance, 0.02);
    }
    @Override
    public boolean withdraw(double amnt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'withdraw'");
    }
    @Override
    public boolean applyInterest() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyInterest'");
    }

}
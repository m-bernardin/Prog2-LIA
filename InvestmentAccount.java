import java.time.LocalDate;
public class InvestmentAccount extends EarningsAccount{
    private LocalDate dateOpened;
    public InvestmentAccount(double balance,LocalDate dateOpened, String accountID) {
        super(balance, 0.05, accountID);
        this.dateOpened = dateOpened;
    }
    public LocalDate getDateOpened() {
        return dateOpened;
    }
    public void setDateOpened(LocalDate dateOpened) {
        this.dateOpened = dateOpened;
    }
    @Override
    public boolean withdraw(double amnt) {
        // TODO Auto-generated method stub
        // TODO throws clause
        throw new UnsupportedOperationException("Unimplemented method 'withdraw'");
    }
    @Override
    public boolean applyInterest() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyInterest'");
    }
}
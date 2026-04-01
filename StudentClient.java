import java.sql.Date;
import java.time.LocalDate;
public class StudentClient extends StandardClient{
    private LocalDate dateStatusRenewed;
    private boolean statusValid;
    public StudentClient(String clientID, String username, String password, Date dateLastOpened, Date dateOpened,
            String name, String contact, LocalDate dateStatusRenewed, boolean statusValid) {
        super(clientID, username, password, dateLastOpened, dateOpened, name, contact);
        this.dateStatusRenewed = dateStatusRenewed;
        this.statusValid = statusValid;
        monthlyFee=0;
    }
    @Override
    public boolean applyMonthlyFee() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyMonthlyFee'");
    }
    public LocalDate getDateStatusRenewed() {
        return dateStatusRenewed;
    }
    public void setDateStatusRenewed(LocalDate dateStatusRenewed) {
        this.dateStatusRenewed = dateStatusRenewed;
    }
    public boolean isStatusValid() {
        return statusValid;
    }
    public void setStatusValid(boolean statusValid) {
        this.statusValid = statusValid;
    }
    @Override
    protected boolean maintain() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'maintain'");
    }
}
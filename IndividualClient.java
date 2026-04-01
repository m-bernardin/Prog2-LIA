import java.sql.Date;
public class IndividualClient extends StandardClient{
    public IndividualClient(String clientID, String username, String password, Date dateLastOpened, Date dateOpened,
            String name, String contact) {
        super(clientID, username, password, dateLastOpened, dateOpened, name, contact);
        //TODO Auto-generated constructor stub
    }
    @Override
    protected boolean maintain() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'maintain'");
    }
    @Override
    public boolean applyMonthlyFee() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyMonthlyFee'");
    }
}
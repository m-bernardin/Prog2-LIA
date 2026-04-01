import java.sql.Date;
public class VipClient extends PremiumClient{
    private final static double EXTRA_INTEREST=0.01;
    public static double getExtraInterest() {
        return EXTRA_INTEREST;
    }
    private String name;
    private String contact;
    public VipClient(String clientID, String username, String password, Date dateLastOpened, Date dateOpened,
            boolean rewardsProgramMember, String name, String contact) {
        super(clientID, username, password, dateLastOpened, dateOpened, rewardsProgramMember);
        this.name = name;
        this.contact = contact;
        monthlyFee=0;
    }
    @Override
    public boolean applyMonthlyFee() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyMonthlyFee'");
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    @Override
    protected boolean maintain() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'maintain'");
    }
}
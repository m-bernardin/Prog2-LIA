package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.*;;
public class ClientTest {
    private String testName;
    private IndividualClient individual1;
    private StudentClient student1;
    private CorporateClient corporate1;
    private VipClient vip1;
    private ArrayList<Account> sampleAccounts;
    @BeforeEach
    public void build(){
        try {
            individual1=new IndividualClient("example","Example01!",true,"John Smith","johnSmith@example.org");
            student1=new StudentClient("example","Example01!",true,"Jane Doe","janeDoe@example.org");
            corporate1=new CorporateClient("example","Example01!",true,true,"Unicorporated Inc.",buildCorporateContacts());
            vip1=new VipClient("example","Example01!",true,false,"Nomen Nescio","nomenNescio@sample.gov");
            sampleAccounts=buildSampleAccounts();
        } catch (InvalidTypeException e) {
            System.out.println("**An error occured while running test "+testName+"; could not create test clients...");
            return;
        }
    }
    private ArrayList<Account> buildSampleAccounts() throws InvalidTypeException {
        ArrayList<Account> accounts=new ArrayList<>();
        accounts.add(new ChequeingAccount(0));
        accounts.add(new InvestmentAccount(0));
        accounts.add(new SavingsAccount(0));
        return accounts;
    }
    private ArrayList<String> buildCorporateContacts() {
        ArrayList<String> contacts=new ArrayList<>();
        contacts.add("jeanTremblay@unincInc.com");
        contacts.add("unoWHo@unincInc.com");
        contacts.add("JaneQPublic@unicInc.com");
        return contacts;
    }
    @AfterEach
    public void teardown(){
        individual1=null;
        student1=null;
        corporate1=null;
        vip1=null;
        sampleAccounts=null;
    }
    @Test
    public void testCalculateTotalMonthlyFeeEmpty(){
        assertEquals(0,individual1.calculateTotalMonthlyFee());
    }
    @Test
    public void testCalculateTotalMonthlyFeeOne(){
        individual1.addAccount(sampleAccounts.get(0).getAccountID());
        assertEquals(10, individual1.calculateTotalMonthlyFee());
    }
    @Test
    public void testCalculateTotalMonthlyFeeTwo(){
        individual1.addAccount(sampleAccounts.get(0).getAccountID());
        individual1.addAccount(sampleAccounts.get(1).getAccountID());
        assertEquals(20,individual1.calculateTotalMonthlyFee());
    }
    @Test
    public void testMonthlyFeesWaivedStudent(){
        student1.addAccount(sampleAccounts.get(0).getAccountID());
        assertEquals(0,student1.calculateTotalMonthlyFee());
        student1.addAccount(sampleAccounts.get(1).getAccountID());
        assertEquals(0,student1.calculateTotalMonthlyFee());
    }
    @Test
    public void testMonthlyFeesWaivedVip(){
        vip1.addAccount(sampleAccounts.get(0).getAccountID());
        assertEquals(0,vip1.calculateTotalMonthlyFee());
        vip1.addAccount(sampleAccounts.get(1).getAccountID());
        assertEquals(0,vip1.calculateTotalMonthlyFee());
    }
    @Test
    public void testAddManager(){
        build();
        
    }
}
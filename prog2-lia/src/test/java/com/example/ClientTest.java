package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.*;
public class ClientTest {
    private IndividualClient individual1;
    private StudentClient student1;
    private CorporateClient corporate1;
    private VipClient vip1;
    private ArrayList<Account> sampleAccounts;
    @BeforeEach
    public void build() throws InvalidTypeException{
        individual1=new IndividualClient("example","Example01!",true,"John Smith","johnSmith@example.org");
            student1=new StudentClient("example","Example01!",true,"Jane Doe","janeDoe@example.org");
            corporate1=new CorporateClient("example","Example01!",true,true,"Unicorporated Inc.",buildCorporateContacts());
            vip1=new VipClient("example","Example01!",true,false,"Nomen Nescio","nomenNescio@sample.gov");
            sampleAccounts=buildSampleAccounts();
    }
    private ArrayList<Account> buildSampleAccounts() throws InvalidTypeException {
        ArrayList<Account> accounts=new ArrayList<>();
        accounts.add(new ChequeingAccount(0));
        accounts.add(new InvestmentAccount(0,LocalDate.now()));
        accounts.add(new SavingsAccount(0));
        return accounts;
    }
    private ArrayList<String> buildCorporateContacts() {
        ArrayList<String> contacts=new ArrayList<>();
        contacts.add("jeanTremblay@unincInc.com");
        contacts.add("unoWho@unincInc.com");
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
    public void testAddManager(){
        corporate1.addManager("TCMits@unincInc.com");
        String newestManager=corporate1.getClientManagerContacts().get(corporate1.getClientManagerContacts().size()-1);
        boolean same=newestManager.equals("TCMits@unincInc.com");
        assertTrue(same);
    }
    @Test
    public void testRemoveManagerNegative(){
        boolean removed=corporate1.removeManager("TCMits@unincInc.com");
        assertFalse(removed);
    }
    @Test
    public void testRemoveManagerPositive(){
        boolean removed=corporate1.removeManager("unoWho@unincInc.com");
        assertTrue(removed);
    }
    @Test
    public void testValidateStatusExpires(){
        student1.setStatusExpiryDate(LocalDate.parse("1992-12-01"));
        student1.validateStatus();
        assertFalse(student1.isStatusValid());
    }
    @Test
    public void testValidateStatusValid(){
        student1.setStatusExpiryDate(LocalDate.parse("2371-04-23"));
        student1.validateStatus();
        assertTrue(student1.isStatusValid());
    }
}
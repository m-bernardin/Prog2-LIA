package com.example;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class AccountTest {
    ChequeingAccount chequeing1;
    SavingsAccount savings1;
    InvestmentAccount investment1;
    InvestmentAccount investment2;
    @BeforeEach
    public void build() throws InvalidTypeException{
        chequeing1=new ChequeingAccount(50);
        savings1=new SavingsAccount(400);
        investment1=new InvestmentAccount(250,LocalDate.parse("2020-03-11"));
        investment2=new InvestmentAccount(250,LocalDate.now());
    }
    @AfterEach
    public void teardown(){
        chequeing1=null;
        savings1=null;
        investment1=null;
        investment2=null;
    }
    @Test
    public void testWithdrawPositive(){
        assertDoesNotThrow(()->chequeing1.withdraw(25));
        assertDoesNotThrow(()->savings1.withdraw(25));
    }
    @Test
    public void testWithdrawTooMuch(){
        assertThrows(InsufficientFundsException.class,()->chequeing1.withdraw(800));
        assertThrows(InsufficientFundsException.class,()->savings1.withdraw(800));
    }
    @Test
    public void testWithdrawUnlockedInvestmentSufficient(){
        assertDoesNotThrow(()->investment1.withdraw(125));
    }
    @Test
    public void testWithdrawUnlockedInvestmentInSufficient(){
        assertThrows(InsufficientFundsException.class,()->investment1.withdraw(500));
    }
    @Test
    public void testWithdrawLockedInvestmentNegative(){
        assertThrows(InvestmentLockException.class,()->investment2.withdraw(125));
    }
    @Test
    public void testConvertAllCurrenciesNoThrow(){
        assertDoesNotThrow(()->Account.convert(50,"USD"));
        assertDoesNotThrow(()->Account.convert(50,"GBP"));
        assertDoesNotThrow(()->Account.convert(50,"JPY"));
        assertDoesNotThrow(()->Account.convert(50,"EUR"));
        assertDoesNotThrow(()->Account.convert(50,"AUD"));
        assertDoesNotThrow(()->Account.convert(50,"CHF"));
    }
    @Test
    public void testConvertInvalidThrows(){
        assertThrows(InvalidTypeException.class,()->Account.convert(50,"CAD"));
    }
    // TODO test alt currency deposit if time to refactor
    // TODO test apply interest if time to refactor
}
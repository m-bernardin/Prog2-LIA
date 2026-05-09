package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
public class TransactionTest {
    @Test
    public void testValidBlank(){
        Transaction blank=new Transaction();
        assertEquals("No more transactions to show...",blank.toString());
    }
    @Test
    public void testTestValidAssociatedGivingAccount(){
        Transaction t=null;
        try {
            t=new Transaction(42,"r42","g42",true);
        } catch (InvalidTypeException e) {
            System.out.println("**Error occured creating test transaction");
            fail("Error occured creating test transaction", e);
        }
        assertTrue(t.isAssociatedTo("g42"));
    }
    @Test
    public void testTestInvalidAssociatedGivingAccount(){
        Transaction t=null;
        try {
            t=new Transaction(42,"r42","g42",true);
        } catch (InvalidTypeException e) {
            System.out.println("**Error occured creating test transaction");
            fail("Error occured creating test transaction", e);
        }
        assertFalse(t.isAssociatedTo("t42"));
    }
    @Test
    public void testTestValidAssociatedRecievingAccount(){
        Transaction t=null;
        try {
            t=new Transaction(42,"r42","g42",true);
        } catch (InvalidTypeException e) {
            System.out.println("**Error occured creating test transaction");
            fail("Error occured creating test transaction", e);
        }
        assertTrue(t.isAssociatedTo("r42"));
    }
    @Test
    public void testTestInvalidAssociatedRecievingAccount(){
        Transaction t=null;
        try {
            t=new Transaction(42,"r42","g42",true);
        } catch (InvalidTypeException e) {
            System.out.println("**Error occured creating test transaction");
            fail("Error occured creating test transaction", e);
        }
        assertFalse(t.isAssociatedTo("t42"));
    }
}
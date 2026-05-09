package com.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
public class AppTest{
    @Test
    public void testHasEmptyFieldsPositive(){
        String[] fields={"field1","field2",""," field4"," "};
        assertTrue(App.hasEmptyFields(fields));
    }
    @Test
    public void testHasEmptyFieldsNegative(){
        String[] fields={"field1","field2","field3","field4","field5"};
        assertFalse(App.hasEmptyFields(fields));
    }
}
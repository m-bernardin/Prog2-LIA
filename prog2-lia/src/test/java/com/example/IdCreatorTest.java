package com.example;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class IdCreatorTest {
    @Test
    public void testInvalidTypeThrows(){
        assertThrows(InvalidTypeException.class, ()->IdCreator.createID(9, 0));
    }
    @Test
    public void testInvalidSubtypeClientThrows(){
        assertThrows(InvalidTypeException.class,()->IdCreator.createID(1,5));
    }
    @Test
    public void testInvalidSubtypeAccountThrows(){
        assertThrows(InvalidTypeException.class,()->IdCreator.createID(1,5));
    }
    @Test
    public void testValidTypeNoThrows(){
        assertDoesNotThrow(()->IdCreator.createID(1, 1));
        assertDoesNotThrow(()->IdCreator.createID(2, 1));
        assertDoesNotThrow(()->IdCreator.createID(3, 1));
    }
    @Test
    public void testValidSubtypeClientNoThrows(){
        assertDoesNotThrow(()->IdCreator.createID(1,1));
        assertDoesNotThrow(()->IdCreator.createID(1,2));
        assertDoesNotThrow(()->IdCreator.createID(1,3));
        assertDoesNotThrow(()->IdCreator.createID(1,4));
    }
    @Test
    public void testValidSubtypeAccountNoThrows(){
        assertDoesNotThrow(()->IdCreator.createID(2,1));
        assertDoesNotThrow(()->IdCreator.createID(2,2));
        assertDoesNotThrow(()->IdCreator.createID(2,3));
    }
    // TODO write validate checksum test if time for making/using it
}
package com.example;

/**
 * Thrown when an incorrect type is used in a selector.
 * @author Mathieu Bernardin
 */
public class InvalidTypeException extends Exception{

    public InvalidTypeException(String message) {
        super(message);
    }
    
}
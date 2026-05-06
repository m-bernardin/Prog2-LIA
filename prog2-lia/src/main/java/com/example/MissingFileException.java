package com.example;
/**
 * Thrown when the driver attempts to access a file which does not exist or is otherwise inaccessible.
 * @author Mathieu Bernardin
 */
public class MissingFileException extends Exception{
    /**
     * Constructor for this class. Forces a message to be specified when throwing this exception.
     * @param message - the specified message.
     */
    public MissingFileException(String message) {
        super(message);
    }
}

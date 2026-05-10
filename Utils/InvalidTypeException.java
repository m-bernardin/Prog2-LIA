/**
 * Thrown when an incorrect type is used in a selector.
 * @author Mathieu Bernardin
 */
public class InvalidTypeException extends Exception{
    /**
     * Constructor for this exception. Forces a message to be included with the exception throw.
     * @param message
     */
    public InvalidTypeException(String message) {
        super(message);
    }
    
}
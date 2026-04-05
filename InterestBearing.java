/**
 * Implementing this interface allows a class to apply an interest rate to its balance.
 * @author Mathieu Bernardin
 */
public interface InterestBearing {
    /**
     * Applies an interest rate base on implementation.
     * @return true if the operation was succesful; false otherwise.
     */
    boolean applyInterest();
}
/**
 * Implementing this interface allows the class to be the target of a maintainance operation.
 * @author Mathieu Bernardin
 */
public interface Maintainable {
    /**
     * Deducts a monthly fee from the Client's primary chequing account.
     * @return true if the operation was successful; false otherwise.
     */
    boolean applyMonthlyFee();
}
package com.example;

/**
 * Thrown when a user tries to withdraw from an investment account which has not yet been active for a year.
 * @author Mathieu Bernardin
 */
public class InvestmentLockException extends Exception{
    /**
     * Constructs an InvestmentLockException with a pre-formatted message to be displayed to the user, detailing the time remaining until the account may be withdrawn from.
     * @param remainingTime - the amount of time until the account may be withdrawn from 
     */
    public InvestmentLockException(String remainingTime){
        super("Not enough time since passed since account opened; remaining time: "+remainingTime);
    }
    /**
     * Constructs an InvestmentLockException which automatically prints the stack trace to console.
     * Should only be used for debugging.
     */
    public InvestmentLockException(){
        printStackTrace();
    }
}
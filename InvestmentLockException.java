public class InvestmentLockException extends Exception{
    public InvestmentLockException(String remainingTime){
        super("Not enough time since passed since account opened; remaining time: "+remainingTime);
    }
    public InvestmentLockException(boolean debug){
        if(debug)printStackTrace();
    }
}
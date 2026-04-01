public class IdCreator {
    public static String createID(int type,int subtype) throws InvalidTypeException{
        switch(type){
            case 0:
                // TODO client
                return createClientID(subtype);
            case 1:
                // TODO account
                return createAccountID(subtype);
            case 2:
                // TODO transaction
                return createTransactionID();
            default:
                throw new InvalidTypeException();
        }
    }
    private static String createTransactionID() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTransactionID'");
    }
    private static String createAccountID(int subtype) throws InvalidTypeException{
        // TODO Auto-generated method stub
        String ID="";
        switch (subtype) {
            case 1:
                // TODO chequing
            case 2:
                // TODO investment
            case 3:
                // TODO savings
            default:
                throw new InvalidTypeException();
        }
    }
    private static String createClientID(int subtype) throws InvalidTypeException{
        // TODO Auto-generated method stub
        String ID="";
        switch (subtype) {
            case 1:
                // TODO individual
            case 2:
                // TODO student
            case 3:
                // TODO corporate
            case 4:
                // TODO vip
            default:
                throw new InvalidTypeException();
        }
    }
    private static String convertBase64(int num){
        String conversion="";
        while(num>=64){
            conversion+=digitConvert(num%64);
            num/=64;
        }
        // TODO logic for flipping result
        return conversion;
    }
    private static String digitConvert(int num) {
        if(num>=0&&num<10)return num+"";
        if(num>=10&&num<36)return null; // TODO write logic for lower case
        if(num>=36&&num<62)return null; // TODO write logic for upper case
        if(num>=62&&num<64)return null; // TODO write logic for + and =
        return null;
    }
}
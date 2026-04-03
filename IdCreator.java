import java.util.Scanner;

public class IdCreator {
    public static String createID(int type,int subtype) throws InvalidTypeException{
        switch(type){
            case 1:
                // TODO client
                return createClientID(subtype);
            case 2:
                // TODO account
                return createAccountID(subtype);
            case 3:
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
        String ID="1";
        switch (subtype) {
            case 1:
                ID+=1;
            case 2:
                ID+=2;
            case 3:
                ID+=3;
            case 4:
                ID+=4;
            default:
                throw new InvalidTypeException();
        }
    }
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.print("enter a number\n> ");
        System.out.println(convertBase64(input.nextInt()));
    }
    private static String convertBase64(int num){
        int digits=0,digitsAid=num,arrayPointer=0;
        while(digitsAid>0){
            ++digits;
            digitsAid/=64;
        }
        char[] inverseConversion=new char[digits];
        for(;arrayPointer<inverseConversion.length;++arrayPointer){
            inverseConversion[arrayPointer]=digitConvert(num%64);
            num/=64;
        }
        String conversion="";
        for(int i=inverseConversion.length-1;i>=0;--i){
            conversion+=inverseConversion[i];
        }
        return conversion;
    }
    private static char digitConvert(int num) {
        if(num>=0&&num<10)return (char)(48+(num));
        if(num>=10&&num<36)return (char)(97+(num-10));
        if(num>=36&&num<62)return (char)(65+(num-36));
        if(num>=62&&num<64)return (char)(58+(num-62));
        return 0;
    }
}
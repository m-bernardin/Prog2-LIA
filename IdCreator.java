import java.util.Random;
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
        String ID="2";
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
                break;
            case 2:
                ID+=2;
                break;
            case 3:
                ID+=3;
                break;
            case 4:
                ID+=4;
                break;
            default:
                ID+=9;
                break;
        }
        if(ID.charAt(1)=='9')throw new InvalidTypeException();
        ID=convertBase64(Integer.parseInt(ID));
        ID+=generateRandom();
        int checksum=0;
        for(int i=0;i<ID.length();++i){
            checksum+=convertBase10(ID.charAt(i)+"");
        }
        ID+=convertBase64(checksum);
        return ID;
    }
    private static String generateRandom() {
        // TODO add reserved IDs
        Random gen=new Random();
        return convertBase64(gen.nextInt(1073741823));
    }
    // public static void main(String[] args) {
    //     boolean running=true;
    //     Scanner input=new Scanner(System.in);
    //     while(running){
    //         System.out.print("Please select an option\n1. convert to base 64\n2. convert to base 10\n3. create client ID\n4. quit\n> ");
    //         switch (input.nextInt()) {
    //             case 1:
    //                 System.out.print("enter a number\n> ");
    //                 System.out.println(convertBase64(input.nextInt()));
    //                 break;
    //             case 2:
    //                 System.out.print("enter a number\n> ");
    //                 System.out.println(convertBase10(input.next()));
    //                 break;
    //             case 3:
    //                 System.out.print("enter a subtype (1-4)\n> ");
    //                 try {
    //                     System.out.println(createClientID(input.nextInt()));
    //                 } catch (InvalidTypeException e) {
    //                     System.out.println("invalid type");
    //                 }
    //                 break;
    //             case 4:
    //                 input.close();
    //                 running=false;
    //                 break;
    //             default:
    //                 System.out.println("Please select a valid option...");
    //                 break;
    //         }
    //     }
    // }
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
    private static int convertBase10(String num){
        int base10=0;
        for(int charPointer=num.length()-1, pow=0;charPointer>=0;--charPointer,++pow){
            base10+=parse64Digit(num.charAt(charPointer))*Math.pow(64,pow);
        }
        return base10;
    }
    private static int parse64Digit(char digit) {
        if(digit>='0'&&digit<='9')return (int)(digit-48);
        if(digit>='a'&&digit<='z')return (int)(digit-87);
        if(digit>='A'&&digit<='Z')return (int)(digit-29);
        if(digit>=':'&&digit<=';')return (int)(digit+4);
        return 0;
    }
}
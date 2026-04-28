package com.example;

import java.util.Random;
import java.util.Scanner; // TODO remove after testing done
/**
 * Allows for creation of pseudo-random IDs for use with Client, Account, and Transactions classes; Generates IDs based on type of ID and, where applicable subtype.
 * IDs are composed of a type-subtype identifier, and a unique pseudo-random identifier, appended with a checksum validator, all in base 64 numbering for brevity.
 * @author Mathieu Bernardin
 */
public class IdCreator {
    /**
     * Main way with which other classes interact with this class. Outputs an unchecked 
     * @param type - the type of ID to be created.
     * @param subtype - the subtype of ID to be created.
     * @return the created ID.
     * @throws InvalidTypeException - if the type or subtype provided is invalid.
     */
    public static String createID(int type,int subtype) throws InvalidTypeException{
        String ID;
        switch(type){
            case 1:
                ID=createClientID(subtype);
                break;
            case 2:
                ID=createAccountID(subtype);
                break;
            case 3:
                ID=createTransactionID();
                break;
            default:
                ID="?";
        }
        if(ID.equals("?"))throw new InvalidTypeException();
        ID+=generateRandom();
        int checksum=0;
        for(int i=0;i<ID.length();++i){
            checksum+=convertBase10(ID.charAt(i)+"");
        }
        ID+="-"+convertBase64(checksum);
        return ID;
    }
    /**
     * Generates the identifier component for an ID of type Transaction.
     * @return the identifier component generated.
     */
    private static String createTransactionID() {
        return "u";
    }
    /**
     * Generates the identifier component for an ID of type Account, based off a provided subtype.
     * @param subtype - the subtype of account
     * @return the identifier component generated.
     * @throws InvalidTypeException - if the subtype provided is invalid.
     */
    private static String createAccountID(int subtype) throws InvalidTypeException{
        String ID="2";
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
            default:
                ID+=9;
                break;
        }
        if(ID.charAt(1)=='9')throw new InvalidTypeException();
        ID=convertBase64(Integer.parseInt(ID));
        return ID;
    }
    /**
     * Generates the identifier component for an ID of type Client, based off a provided subtype.
     * @param subtype - the subtype of client
     * @return the identifier component generated.
     * @throws InvalidTypeException - if the subtype provided is invalid.
     */
    private static String createClientID(int subtype) throws InvalidTypeException{
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
        return ID;
    }
    /**
     * Generates a pseudo-random identifier for the identifier component of an ID. Verifies the ID would not be in a list of reserved ID - These IDs include admin-specific IDs and IDs which, once converted to base 64 would include profanity.
     * @return the identifier generated.
     */
    private static String generateRandom() {
        // TODO add reserved IDs
        Random gen=new Random();
        return convertBase64(gen.nextInt(1073741823));
    }
    /**
     * debug method for testing base 64 conversion and id creation
     * @param args
     */
    public static void main(String[] args) {
        // TODO remove after testing done
        boolean running=true;
        Scanner input=new Scanner(System.in);
        while(running){
            System.out.print("Please select an option\n1. convert to base 64\n2. convert to base 10\n3. create client ID\n4. quit\n> ");
            switch (input.nextInt()) {
                case 1:
                    System.out.print("enter a number\n> ");
                    System.out.println(convertBase64(input.nextInt()));
                    break;
                case 2:
                    System.out.print("enter a number\n> ");
                    System.out.println(convertBase10(input.next()));
                    break;
                case 3:
                    System.out.print("enter type (1-3)\n> ");
                    switch (input.nextInt()) {
                        case 1:
                            System.out.print("enter subtype (1-4)\n> ");
                            try {
                                System.out.println(createID(1, input.nextInt()));
                            } catch (InvalidTypeException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                                System.out.print("enter subtype (1-3)\n> ");
                            try {
                                System.out.println(createID(2, input.nextInt()));
                            } catch (InvalidTypeException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            break;
                        case 3:
                            try {
                                System.out.println(createID(3, 0));
                            } catch (InvalidTypeException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            break;
                        default:
                            System.out.println("invalid");
                            break;
                    }
                    break;
                case 4:
                    input.close();
                    running=false;
                    break;
                default:
                    System.out.println("Please select a valid option...");
                    break;
            }
        }
    }
    /**
     * Converts a given number to base 64 numbering.
     * @param num - the number to be converted.
     * @return the converted number.
     */
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
    /**
     * Converts a number to a single base 64 digit.
     * @param num - the number to be converted - must be less than 64.
     * @return the converted digit.
     */
    private static char digitConvert(int num) {
        if(num>=0&&num<10)return (char)(48+(num));
        if(num>=10&&num<36)return (char)(97+(num-10));
        if(num>=36&&num<62)return (char)(65+(num-36));
        if(num>=62&&num<64)return (char)(58+(num-62));
        return 0;
    }
    /**
     * Converts a given number in base 64 back to bsae 10.
     * @param num - the number to be converted
     * @return the converted number.
     */
    private static int convertBase10(String num){
        int base10=0;
        for(int charPointer=num.length()-1, pow=0;charPointer>=0;--charPointer,++pow){
            base10+=parse64Digit(num.charAt(charPointer))*Math.pow(64,pow);
        }
        return base10;
    }
    /**
     * Converts a single digit in base 64 to its equivalent in base 10.
     * @param digit - the digit to be converted.
     * @return the converted number.
     */
    private static int parse64Digit(char digit) {
        if(digit>='0'&&digit<='9')return (int)(digit-48);
        if(digit>='a'&&digit<='z')return (int)(digit-87);
        if(digit>='A'&&digit<='Z')return (int)(digit-29);
        if(digit>=':'&&digit<=';')return (int)(digit+4);
        return 0;
    }
}
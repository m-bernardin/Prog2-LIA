import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class DateConverter {
    public static void main(String[] args) {
        boolean running=true;
        Scanner input=new Scanner(System.in);
        while(running){
            System.out.print("Please select an option\n1. convert to date\n2. convert to datetime\n3. quit\n> ");
            switch (input.nextInt()) {
                case 1:
                    System.out.print("enter date\n> ");
                    LocalDate date=LocalDate.parse(input.next());
                    System.out.println("year: "+date.getYear());
                    System.out.println("month: "+date.getMonth());
                    System.out.println("day: "+date.getDayOfMonth());
                    System.out.println("toString: "+date.toString());
                    break;
                case 2:
                    System.out.print("enter date\n> ");
                    LocalDateTime datetime=LocalDateTime.parse(input.next());
                    System.out.println("year: "+datetime.getYear());
                    System.out.println("month: "+datetime.getMonth());
                    System.out.println("day: "+datetime.getDayOfMonth());
                    System.out.println("hour: "+datetime.getHour());
                    System.out.println("minute: "+datetime.getMinute());
                    System.out.println("second: "+datetime.getSecond());
                    System.out.println("nano: "+datetime.getNano());
                    System.out.println("toString: "+datetime.toString());
                    break;
                case 3:
                    running=false;
                    System.out.println("Goodbye :)");
                    input.close();
                    break;
                default:
                    System.out.println("Please select a valid option...");
                    break;
            }
        }
    }
}
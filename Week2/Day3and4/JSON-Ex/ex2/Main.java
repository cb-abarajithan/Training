package ex2;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

/**
 * Week2 (Day1/2) - Main class for PhoneDirectory
 */
public class Main {

    public static void main(String[] args) {

        if(args.length < 1){
            System.out.println("usage: java ex2/Main <input_json>");
            System.out.println("example: java ex2/Main ex2/persons.json");
            System.exit(-1);
        }

        Scanner scan = new Scanner(System.in);
        PhoneDirectoryController controller = null;
        try{
            //controller = new PhoneDirectoryController(PhoneDirectoryController.getSampleData());
            controller = new PhoneDirectoryController(PhoneDirectoryController.readFromJSON(args[0]));
            
            System.out.println("PhoneDirectory Menu:");
            System.out.println("1 - Display All\n2 - Search by Number\n3 - Search by Name\n4 - Search by exact name\n0 - Exit\n");

            int choice;
            do{

                System.out.print("> ");
                choice = scan.nextInt();

                switch (choice) {
                    case 1:
                        controller.displayAll();
                        break;
                    case 2:
                        System.out.print("Number > ");
                        controller.searchByNumber(scan.nextLong());
                        break;
                    case 3:
                        System.out.print("Name > ");
                        controller.searchByName(scan.next().toLowerCase());
                        break;
                    case 4:
                        System.out.print("Exact Name > ");
                        controller.searchByNameExact(scan.next().toLowerCase());
                        break;
                    default: break;
                }

            }while(choice!=0);
            scan.close();

        }catch(ParseException e){
            System.out.println("Unable to read JSONfile: %s" + args[0]);
        }

    }
    
}   
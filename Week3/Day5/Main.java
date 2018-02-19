import java.io.IOException;
import java.util.Scanner;

import java.sql.*;

import org.json.simple.parser.ParseException;

/**
 * Week3  (Day5) - Main class for PhoneDirectory
 */
public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        PhoneDirectoryController controller = null;
        try{
            //controller = new PhoneDirectoryController(PhoneDirectoryController.readFromJSON(args[0]));
            controller = new PhoneDirectoryController();
            
            System.out.println("PhoneDirectory Menu:");
            System.out.println("1 - Display All\n2 - Search by Number\n3 - Search by Name\n4 - Search by exact name");
            System.out.println("5 - Add new\n6 - Add number to person\n7 - Edit name\n8 - Edit number\n9 - Load Sample data\n0 - Exit\n");

            int choice;
            do{

                System.out.print("> ");
                choice = scan.nextInt();

                switch (choice) {
                    case 1:
                        controller.displayAll();
                        break;
                    case 2:
                        System.out.println("Search by Number");
                        System.out.print("Number > ");
                        controller.searchByNumber(scan.nextLong());
                        break;
                    case 3:
                        System.out.println("Search by Name");
                        System.out.print("Name > ");
                        controller.searchByName(scan.next().toLowerCase());
                        break;
                    case 4:
                        System.out.println("Search by Exact name");
                        System.out.print("Exact Name > ");
                        controller.searchByNameExact(scan.next().toLowerCase());
                        break;
                    case 5:
                        System.out.println("Add new");
                        System.out.print("Enter name > ");
                        String name = scan.next();
                        System.out.print("Enter address > ");
                        String addr = scan.next();
                        System.out.println("Number and type (mobile, work, home)");
                        System.out.print("Enter > ");
                        controller.insertNew(name, addr, scan.nextLong(), scan.next());
                        break;
                    case 6:
                        System.out.println("Add Number to person");
                        System.out.println("Name, Number and type (mobile, work, home)");
                        System.out.print("Enter > ");
                        controller.insertNumber(scan.next(), scan.nextLong(), scan.next());
                        break;
                    case 7:
                        controller.displayAll();
                        System.out.println("Edit name");
                        System.out.print("Old Name > ");
                        String oldName = scan.next();
                        System.out.print("New Name > ");
                        String newName = scan.next();
                        controller.editName(oldName, newName);
                        break;
                    case 8:
                        controller.displayAll();
                        System.out.println("Edit number");
                        System.out.print("Old No > ");
                        long oldNo = scan.nextLong();
                        System.out.print("New No > ");
                        long newNo = scan.nextLong();
                        controller.editNo(oldNo, newNo);
                        break;
                    case 9:
                        controller.readFromJSON();
                        break;
                    default: break;
                }

            }while(choice!=0);
            scan.close();

        }catch(SQLException e){
            System.out.println("Database error: "+e.getMessage());
        }catch(Exception e){
            System.out.println("Unable to read JSONfile: %s" + args[0]);
        }finally{
            try{
                if(controller!=null){
                    controller.close();
                }
            }catch(SQLException e){}
        }

    }
    
}   
import java.util.Scanner;
/**
 * Week1 (Day3) - Main class for Income Tax calculator
 */
public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter no of employees: ");
        int n = scan.nextInt();

        Employee[] employees = new Employee[n];

        System.out.println("Enter name, gender, income: ");
        String name;
        char g;
        double income;
        for(int i=0;i<n;i++){
            
            name = scan.next();
            g = Character.toUpperCase(scan.next(".").charAt(0));
            income = scan.nextDouble();

            employees[i] = new Employee(name, g, income);

        }
        System.out.println("\nEmployee Name\tGender\tIncome\tTaxAmnt");
        for(Employee e: employees){
            System.out.printf("%s\t%c\t%.2f\t%.2f\n",e.getName(),e.getGender(),e.getTaxableIncome(),e.getTaxAmount());
        }

        scan.close();

    }
    
}
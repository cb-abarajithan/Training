import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Week1 (Day5) - Main class
 */
public class Main {

    public static void main(String[] args) {
        
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Tony Stark",5000,30));
        employees.add(new Employee("Bruce Wayne",9000,35));
        employees.add(new Employee("Clark Kent",1000,36));
        employees.add(new Employee("Bruce Banner",2500,28));

        System.out.println("\nSorted by name: ");
        Collections.sort(employees);
        printEmployees(employees);

        System.out.println("\nSorted by salary: ");
        Collections.sort(employees, (e1,e2) -> {
            if(e1.getSalary() > e2.getSalary()) return 1;
            if(e1.getSalary() < e2.getSalary()) return -1;
            return 0;
        });
        printEmployees(employees);


        System.out.println("\nSorted by age: ");
        Collections.sort(employees, (e1,e2) -> e1.getAge() - e2.getAge());
        printEmployees(employees);

    }

    private static void printEmployees(ArrayList<Employee> employees){
        for(Employee e: employees){
            System.out.printf("%s\t%.1f\t%d\n", e.getName(), e.getSalary(), e.getAge());
        }
    }
    
}
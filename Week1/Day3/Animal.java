import java.util.Scanner;

/**
 * Week1 (Day3) - Animal
 */
public class Animal {

    private static int count = 0;

    private String name;

    public Animal(String name){
        this.name = name;
        System.out.println("Created: "+name);
        System.out.println("\nNo of animals created: "+(++count));
    }

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        char ch;
        while(true){

            System.out.println("Enter animal name: ");
            String n = scan.next();

            new Animal(n);

            System.out.print("Continue? (Y/n): ");
            ch = scan.next(".").charAt(0);
            
            if(ch=='n') break;

        }

        scan.close();

    }
    
}
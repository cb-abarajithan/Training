import java.util.Scanner;
/**
 * Week1 (Day2) - Fibonacci series upto N numbers
 */
public class Fibonacci {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter N: ");
        int n = scan.nextInt();

        int a = -1;
        int b = 1;

        for(int i=0;i<n;i++){
            int c = a+b;
            System.out.printf("%d ",c);
            a = b;
            b = c;
        }

        System.out.println();

        scan.close();

    }
    
}
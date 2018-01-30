import java.util.Scanner;

/**
 * Week1 (Day1) - Smallest Power exercise
 */
public class Power {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        int y = scan.nextInt();
        
        int i=1;
        for (int p=x;p<=y;i++,p*=x);

        System.out.println(i);

    }

}
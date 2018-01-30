import java.util.Scanner;
/**
 * Week1 (Day2) - Pattern program
 */
public class Pattern {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        int i = 1;
        while(i<=n){

            // Print spaces
            for(int j=1;j<=n-i;j++){
                System.out.print("  ");
            }

            // Print till center
            for(int j=1;j<=i;j++){
                System.out.print(j+" ");
            }

            // Print from center-1 to 1
            for(int j=i-1;j>=1;j--){
                System.out.print(j+" ");
            }

            System.out.println();
            i++;

        }

        scan.close();

    }
    
}
import java.util.Scanner;
/**
 * Week1 (Day2) - Program to find the summation of indices of the characters in given input is odd or even.
 */
public class IndicesSum {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter characters: ");
        String chars = scan.nextLine();

        int len = chars.length();
        int sum = 0;

        // ASCII value of 'a' is 65.
        // So 66 is used to assign 1 to 'a'.
        for(int i=0;i<len;i++){
            sum += (66 - (int) chars.charAt(i));
        }

        if(sum%2 == 0){
            System.out.println("Even");
        }else{
            System.out.println("Odd");
        }
        
        scan.close();

    }
    
}
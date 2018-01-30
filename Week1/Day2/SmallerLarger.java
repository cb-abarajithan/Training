import java.util.Scanner;

/**
 * Week1 (Day2) - To find small and large number in an integer array.
 */
public class SmallerLarger {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = scan.nextInt();

        int[] arr = new int[n];

        System.out.printf("Enter %d elements: ",n);
        for(int i=0;i<n;i++){
            arr[i] = scan.nextInt();
        }

        int s = arr[0];
        int l = arr[0];

        for(int i=0;i<n;i++){
            if(arr[i]>l) l = arr[i];
            if(arr[i]<s) s = arr[i];
        }

        System.out.printf("Small: %d, Large: %d\n",s,l);

        scan.close();

    }   
    
}
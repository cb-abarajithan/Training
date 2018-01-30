import java.util.Scanner;

/**
 * Week1 (Day2) - MatrixRotation program
 */
public class MatrixRotation {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter n value (nxn): ");
        int n = scan.nextInt();

        int[][] matrix = new int[n][n];

        System.out.println("Enter matrix values: ");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrix[i][j] = scan.nextInt();
            }
        }
        
        System.out.print("Rotation (1-left, 2-right): ");
        int choice = scan.nextInt();

        transposeMatrix(matrix, n);
        if(choice==1){
            // Left rotation
            System.out.println("Left rotation: \n");
            leftRotate(matrix,n);
        }else{
            // Right rotation
            System.out.println("Right rotation: \n");
            rightRotate(matrix,n);
        }
    
        // Print the matrix
        for(int r=0;r<n;r++){
            for(int c=0;c<n;c++){
                System.out.print(matrix[r][c]+" ");
            }
            System.out.println();
        }

        scan.close();

    }

    private static void transposeMatrix(int[][] matrix,int len){

        for(int r=0;r<len;r++){
            for(int c=r;c<len;c++){
                int temp = matrix[r][c];
                matrix[r][c] = matrix[c][r];
                matrix[c][r] = temp;
            }
        }

    }

    private static void leftRotate(int[][] matrix,int len){
        // Swap col elements
        for(int r=0;r<len;r++){
            for(int c=0,k=len-1;c<k;c++,k--){
                int temp = matrix[c][r];
                matrix[c][r] = matrix[k][r];
                matrix[k][r] = temp;
            }
        }
    }

    private static void rightRotate(int[][] matrix,int len){
        // Swap row elements
        for(int r=0;r<len;r++){
            for(int c=0,k=len-1;c<k;c++,k--){
                int temp = matrix[r][c];
                matrix[r][c] = matrix[r][k];
                matrix[r][k] = temp;
            }
        }
    }
    
}
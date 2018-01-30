import java.util.Scanner;
/**
 * Week1 (Day2) - Simple Interest and Compound Interest program.
 * 
 * SI = P * n * r/100
 * 
 * CI = P * ( (1 +(r/100)) ^ n) - P
 * 
 */
public class Interest {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter Principle amount: ");
        int p = scan.nextInt();

        System.out.print("Enter No. of years: ");
        int n = scan.nextInt();

        System.out.print("Enter Rate of Interest: ");
        double r = scan.nextDouble();

        double si = (p*n*r)/100;
        System.out.printf("Simple Interest (SI): %.2f\n",si);

        double a = 1 + (r/100);
        double ci = p * Math.pow(a, n) - p;
        System.out.printf("Compound Interest (CI): %.2f\n",ci);

        scan.close();

    }
    
}
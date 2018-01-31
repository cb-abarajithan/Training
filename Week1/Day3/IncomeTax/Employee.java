import java.io.ObjectInputStream.GetField;

/**
 * Week1 (Day3) - Employee class for Income Tax calculator
 */
public class Employee {

    private String name;
    private char gender;
    private double taxableIncome;

    public Employee(String name, char gender, double taxableIncome){
        this.name = name;
        this.gender = gender;
        this.taxableIncome = taxableIncome;
    }

    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public double getTaxAmount() {
        return TaxCalculator.calculate(gender, taxableIncome);
    }
    
}
/**
 * Week1 (Day3) - TaxCalculator class for Income Tax calculator
 */
public class TaxCalculator {

    public static double calculate(char gender, double taxableIncome){
        double p;
        if(gender == 'M'){
            p = taxBracketMale(taxableIncome);
        }else{
            p = taxBracketFemale(taxableIncome);
        }
        return p * taxableIncome;
    }

    private static double taxBracketMale(double taxableIncome){
        if(taxableIncome > 0 && taxableIncome <= 100){
            return 0.10;
        }else if(taxableIncome > 100 && taxableIncome <= 500){
            return 0.15;
        }else if(taxableIncome > 500 && taxableIncome <= 1000){
            return 0.17;
        }else if(taxableIncome > 1000 && taxableIncome <= 10000){
            return 0.21;
        }else if(taxableIncome > 10000){
            return 0.25;
        }else{
            return 0;
        }
    }

    private static double taxBracketFemale(double taxableIncome){
        if(taxableIncome > 0 && taxableIncome <= 100){
            return 0.8;
        }else if(taxableIncome > 100 && taxableIncome <= 500){
            return 0.12;
        }else if(taxableIncome > 500 && taxableIncome <= 1000){
            return 0.14;
        }else if(taxableIncome > 1000 && taxableIncome <= 10000){
            return 0.17;
        }else if(taxableIncome > 10000){
            return 0.20;
        }else{
            return 0;
        }
    }
    
}
/**
 * Week1 (Day4) - Bike class
 */
public class Bike extends Vehicle{

    Bike(String name, String brand, String color, String service){
        super(name, brand, color, service);
    }

    public double getServiceCharge() {
        return 200f;
    }

}
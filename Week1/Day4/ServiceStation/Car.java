/**
 * Week1 (Day4) - Car class
 */
public class Car extends Vehicle{

    Car(String name, String brand, String color, String service){
        super(name, brand, color, service);
    }

    public double getServiceCharge() {
        return 250f;
    }

}
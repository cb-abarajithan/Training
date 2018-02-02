/**
 * Week1 (Day4) - Bus class
 */
public class Bus extends Vehicle{

    Bus(String name, String brand, String color, String service){
        super(name, brand, color, service);
    }

    public double getServiceCharge() {
        return 400f;
    }

}
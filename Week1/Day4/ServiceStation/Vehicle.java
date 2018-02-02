/**
 * Week1 (Day4) -  Vehicle Base abstract class
 */
public abstract class Vehicle {

    private String name;
    private String brand;
    private String color;
    private String service;

    Vehicle(String name, String brand, String color, String service){
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
    
    public abstract double getServiceCharge();
    
}
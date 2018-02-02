/**
 * Week1 (Day4) - Customer class
 */
public class Customer extends Person{

    private Invoice invoice;
    private Vehicle vehicle;

    Customer(String name, int age, long contact, Invoice invoice, Vehicle vehicle){
        super(name, age, contact);
        this.invoice = invoice;
        this.vehicle = vehicle;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

}
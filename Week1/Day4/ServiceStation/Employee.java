/**
 * Week1 (Day4) - Employee class
 */
public class Employee extends Customer{

    private int emp_id;

    Employee(int emp_id, String name, int age, long contact, Invoice invoice, Vehicle vehicle){
        super(name, age, contact, invoice, vehicle);
        this.emp_id = emp_id;
    }

    public int getId() {
        return emp_id;
    }

}
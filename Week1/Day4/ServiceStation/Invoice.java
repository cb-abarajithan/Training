/**
 * Week1 (Day4) - Invoice class
 */
class Invoice {

    private String customerName;
    private Vehicle vehicle;
    private double totalAmnt;
    private int employeeId;

    Invoice(String n, Vehicle v, double ta, int employeeId){
        this.customerName = n;
        this.vehicle = v;
        this.totalAmnt = ta;
        this.employeeId = employeeId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getTotalAmnt() {
        return totalAmnt;
    }

    public int getAssignedEmployeeId() {
        return employeeId;
    }

}
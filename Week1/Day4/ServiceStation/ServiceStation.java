import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Week1 (Day4) -  ServiceStation class
 */
public class ServiceStation {

    private String name;
    private String address;
    private long contact;

    private Vehicle[] servicableVehicles;
    private ArrayList<Employee> employees;
    private ArrayList<Customer> customers;

    ServiceStation(String name, String address, long contact, Vehicle[] servicableVehicles){
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.servicableVehicles = servicableVehicles;
        this.employees = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public Vehicle[] getServicableVehicles() {
        return servicableVehicles;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Customer> geCustomers() {
        return customers;
    }

    public void addCustomer(Customer c){
        customers.add(c);
    }

    public void addEmployee(Employee e){
        employees.add(e);
    }

    public Employee getEmployeeById(int id){
        Employee ee = null;
        for(Employee e: employees){
            if(e.getId() == id) return e;
        }
        return ee;
    }

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);

        Vehicle[] vehicles = { new Car("Car", "Any", "Any", "Any"), new Bike("Bike", "Any", "Any", "Any"), new Bus("Bus", "Any", "Any", "Any") };
        ServiceStation station = new ServiceStation("Cool Service Station",
                                    "Adayar, Chennai - 20",
                                    9346547839l,
                                    vehicles);

        // To print service charges of serviceable vehicles.
        System.out.println("Repairable vehicles: ");
        for(Vehicle vehicle: station.getServicableVehicles()){
            System.out.printf("%s\t%.2f\n",vehicle.getName(),vehicle.getServiceCharge());
        }

        station.addEmployee(new Employee(1, "Bruce", 40, 1234567,null,null));
        station.addEmployee(new Employee(2, "Clark", 41, 12345678,null,null));
        station.addEmployee(new Employee(4, "Tony", 45, 123456,null,null));
        station.addEmployee(new Employee(5, "Natasha", 36, 12347789,null,null));
        station.addEmployee(new Employee(10, "Logan", 150, 34343253,null,null));

        // Print employees
        System.out.println("Employees:");
        System.out.println("ID\tName\tAge\tContact no");
        System.out.println("--------------------------------------");
        station.getEmployees()
            .stream()
            .forEach( e -> System.out.printf("%d\t%s\t%d\t%d\n", e.getId(), e.getName(), e.getAge(), e.getContact()));

        Vehicle vehicle = new Car("Audi R8", "Audi", "Red", "Wash");
        Invoice invoice = new Invoice("Diana", vehicle, vehicle.getServiceCharge() + 100, 1);
        station.addCustomer(new Customer("Diana", 60, 4567556,invoice,vehicle));

        vehicle = new Car("Audi A8", "Audi", "Black", "Bumper");
        invoice = new Invoice("Barry", vehicle, vehicle.getServiceCharge() + 200, 5);
        station.addCustomer(new Customer("Barry", 40, 4567556,invoice,vehicle));

        vehicle = new Bike("Apache", "Apache", "Red", "Wash");
        invoice = new Invoice("Tony", vehicle, vehicle.getServiceCharge(), 10);
        Customer tonyEmployee = station.getEmployeeById(4);
        tonyEmployee.setVehicle(vehicle);
        tonyEmployee.setInvoice(invoice);
        station.addCustomer(tonyEmployee);

        vehicle = new Bus("Volvo", "Benz", "White", "Wash");
        invoice = new Invoice("Arthur", vehicle, vehicle.getServiceCharge() + 500, 4);
        station.addCustomer(new Customer("Arthur", 46, 4567556,invoice,vehicle));

        // Print Customers
        System.out.println("\nCustomers:");
        System.out.println("Name\tAge\tContact\tVehicle\tService\tAmount\tAss.Emp.\tIs Employee?");
        System.out.println("---------------------------------------------------------------------------");
        station.geCustomers()
            .stream()
            .forEach( c -> {
                Vehicle v = c.getVehicle();
                Invoice in = c.getInvoice();
                System.out.printf("%s\t%d\t%d\t%s\t%s\t%.2f\t%s\t\t%b\n", 
                                c.getName(), 
                                c.getAge(), 
                                c.getContact(),
                                v.getName(),
                                v.getService(),
                                in.getTotalAmnt(),
                                station.getEmployeeById(in.getAssignedEmployeeId()).getName(), ( c instanceof Employee) );
            });

        

        scan.close();

    }
    
}
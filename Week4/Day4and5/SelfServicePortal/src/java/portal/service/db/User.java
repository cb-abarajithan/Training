package portal.service.db;

public class User {

    private static final String DELIMITER = "~";
    
    private String fname;
    private String lname;
    private String email;
    private Address address;

    User(){
    }

    public User(String fname, String lname, String email, Address addr) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.address = addr;
    }
    
    public String getName(){
        return fname + " " +lname;
    }

    public void setFirstName(String fname) {
        this.fname = fname;
    }

    public String getFirstName() {
        return fname;
    }

    public void setLastName(String lname) {
        this.lname = lname;
    }

    public String getLastName() {
        return lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(fname).append(DELIMITER)
                .append(lname).append(DELIMITER)
                .append(email).append(DELIMITER)
                .append(address.toString())
                .toString();
    }
    
}
package portal.service;

public class User {

    private String fname;
    private String lname;
    private String email;
    private String address;

    User(){
    }

    public User(String fname, String lname, String addr, String email) {
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

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(fname).append("&")
                .append(lname).append("&")
                .append(email).append("&")
                .append(address)
                .toString();
    }
    
}
package phone.book.db;

/**
 *
 * @author cb-abarajithan
 */
public class PersonBuilder {
    
    private long id;
    private String fname;
    private String lname;
    
    private String line1;
    private String line2;
    private String city;
    private String state;
    private int zip;
    private String country;
    
    private final PhoneNumber[] numbers;
    
    public PersonBuilder(){
        this.id = -1;
        this.numbers = new PhoneNumber[3];
    }
    
    public PersonBuilder addId(long id){
        this.id = id;
        return this;
    }
    
    public PersonBuilder addName(String fname, String lname){
        this.fname = fname;
        this.lname = lname;
        return this;
    }
    
    public PersonBuilder addAddressLines(String line1, String line2){
        this.line1 = line1;
        this.line2 = line2;
        return this;
    }
    
    public PersonBuilder addAddressDetails(String city, String state, int zip, String country){
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        return this;
    }
    
    public PersonBuilder addWorkNumber(long num){
        numbers[1] = new PhoneNumber(num, PhoneNumber.Type.WORK);
        return this;
    }
    
    public PersonBuilder addHomeNumber(long num){
        numbers[2] = new PhoneNumber(num, PhoneNumber.Type.HOME);
        return this;
    }
    
    public PersonBuilder addMobileNumber(long num){
        numbers[0] = new PhoneNumber(num, PhoneNumber.Type.MOBILE);
        return this;
    }
    
    public Person build(){
        Address addr = new Address(line1, line2, city, state, zip, country);
        Person p = new Person(fname, lname, addr, numbers);
        if(id != -1) p.setId(id);
        return p;
    }
    
}

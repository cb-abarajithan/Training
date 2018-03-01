package phone.book.db;

import phone.book.db.PhoneNumber;
import phone.book.db.PhoneNumber.Type;

/**
 *
 * @author cb-abarajithan
 */
public class Person {
    
    private long id;
    private final String fname;
    private final String lname;
    private final Address address;
    private final PhoneNumber[] numbers;

    public Person(String fname, String lname, Address address) {
        this.id = -1;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.numbers = new PhoneNumber[3];
    }
    
    public Person(String fname, String lname, Address addr, PhoneNumber[] numbers){
        this.id = -1;
        this.fname = fname;
        this.lname = lname;
        this.address = addr;
        this.numbers = numbers;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return fname;
    }

    public String getLastName() {
        return lname;
    }
    
    public String getName(){
        return fname + " " + lname;
    }

    public Address getAddress() {
        return address;
    }
    
    public void addNumber(long number, Type type){
        numbers[type.getIndex()] = new PhoneNumber(number, type);
    }
    
    public PhoneNumber getNumber(int index){
        return numbers[index];
    }
    
    public long getNumberFrom(Type type){
        return numbers[type.getIndex()].getNumber();
    }
    
}

package ex2;

import java.util.ArrayList;

/**
 * Week2 (Day1/2) - Person class of a Phone Directory.
 */
public class Person {

    private String name;
    private String address;
    private ArrayList<PhoneNumber> phoneNumbers;

    Person(String name, String address){
        this.name = name;
        this.address = address;
        this.phoneNumbers = new ArrayList<>();
    }

    Person(String name, String address, PhoneNumber number){
        this(name, address);
        addNumber(number);
    }

    public void addNumber(PhoneNumber number) {
        this.phoneNumbers.add(number);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public String toString() {
        return String.format("%s\t%s\t\t%s",name,address,phoneNumbers);
    }

}
/**
 * Week1 (Day4) -  Person Base class
 */
public class Person {

    private String name;
    private int age;
    private long contact;
    
    Person(String name, int age, long contact){
        this.name = name;
        this.age = age;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getContact() {
        return contact;
    }

}
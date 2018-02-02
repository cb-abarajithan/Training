/**
 * Week1 (Day5) - Employee class
 */
public class Employee implements Comparable<Employee>{

    private String name;
    private double salary;
    private int age;

    Employee(String name, double salary, int age){
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public void setSalary(double s){
        this.salary = salary;
    }

    public double getSalary(){
        return salary;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getAge(){
        return age;
    }

    public int compareTo(Employee other){
        return name.compareTo(other.getName());
    }
    
}
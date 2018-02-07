package ex1;

import java.util.ArrayList;

/**
 * Teacher class
 */
public class Teacher extends Person{

    public static String TEACHER = "Teacher";    
    public static String FIELD_SALARY = "Salary";
    public static String FIELD_CLASSES = "Classes Taking Care Of";

    private long salary;
    private ArrayList<String> classes;

    Teacher(String id, String name, String doj, long salary, ArrayList<String> classes){
        super(id, name, doj);
        this.salary = salary;
        this.classes = classes;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public long getSalary() {
        return salary;
    }

    public void addClass(String cls){
        this.classes.add(cls);
    }

    public ArrayList<String> getClasses() {
        return classes;
    }

    public String toString(){
        return id + "," +
            name +", " +
            salary +", " +
            doj +", Classes: " + classes;
    }
    
}
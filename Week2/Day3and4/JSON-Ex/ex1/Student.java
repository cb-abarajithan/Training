package ex1;

import java.util.ArrayList;

/**
 * Student class
 */
public class Student extends Person {

    public static String STUDENT = "Student"; 
    public static String FIELD_STD= "Std";
    public static String FIELD_MARKS= "Marks";

    private String std;
    private ArrayList<Mark> marks;

    Student(String id, String name, String std, String doj, ArrayList<Mark> marks){
        super(id, name, doj);
        this.std = std;
        this.marks = marks;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public String getStd() {
        return std;
    }

    public void addMark(Mark mark){
        this.marks.add(mark);
    }

    public ArrayList<Mark> getMarks() {
        return marks;
    }

    public String toString(){
        return id + "," +
            name +", " +
            std +", " +
            doj +", Marks: " + marks;
    }
    
}
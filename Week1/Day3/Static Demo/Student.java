/**
 * Week1 (Day3) - Student
 */
public class Student {

    private int id;
    private String name;
    private boolean gender;
    private Subject subjects;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean getGender(){
        return gender;
    }

    public void setSubjects(Subject subjects) {
        this.subjects = subjects;
    }

    public Subject getSubjects() {
        return subjects;
    }
    
}
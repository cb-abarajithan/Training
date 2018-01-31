/**
 * Week1 (Day3) - StudentDemo
 */
public class StudentDemo {

    void storeStudentData(Student student){

        student.setName("Abarajithan");
        student.setId(106);
        student.setGender(true);

        Subject subject = new Subject();

        subject.setSubject1("Physics");
        subject.setMarks1(90.1f);

        subject.setSubject2("Maths");
        subject.setMarks2(95.2f);

        subject.setSubject3("CS");
        subject.setMarks3(91.8f);

        student.setSubjects(subject);

    }

    public static void main(String[] args) {
        
        Student student = new Student();

        StudentDemo demo = new StudentDemo();
        demo.storeStudentData(student);

        ResultGenerator.generateResult(student);

    }
    
}
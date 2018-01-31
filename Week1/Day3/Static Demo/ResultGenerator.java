/**
 * Week1 (Day3) - ResultGenerator
 */
public class ResultGenerator {

    static void generateResult(Student student){

        Subject subject = student.getSubjects();

        float total = subject.getMarks1() + subject.getMarks2() + subject.getMarks3();

        float avg = total/3;

        System.out.printf("Id\tName\t\t%s\t%s\t%s\tTotal\tAverage\n",subject.getSubject1(),subject.getSubject2(),subject.getSubject3());
        System.out.printf("%d\t%s\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\n",student.getId(),student.getName(),subject.getMarks1(),subject.getMarks2(),subject.getMarks3(),total,avg);

    }
    
}
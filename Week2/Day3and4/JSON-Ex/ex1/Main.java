package ex1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ex1.Mark;
import ex1.Student;
import ex1.Teacher;

/**
 * Week2 (Day3/4) - Parse JSON string and convert it to Java objects.
 */
public class Main {

    public static void main(String[] args) {
        
        try(BufferedReader reader = new BufferedReader(new FileReader("ex1/students-teachers.json"))){

            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {
                builder.append(str);
            }
            str = builder.toString();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(str);

            JSONObject studentObj = (JSONObject) obj.get(Student.STUDENT);
            JSONObject teacherObj = (JSONObject) obj.get(Teacher.TEACHER);

            // Student
            String id = (String) studentObj.get(Student.FIELD_ID);
            String name = (String) studentObj.get(Student.FIELD_NAME);
            String doj = (String) studentObj.get(Student.FIELD_DOJ);
            String std = (String) studentObj.get(Student.FIELD_STD);

            // Student marks
            ArrayList<Mark> marks = new ArrayList<>();
            JSONArray jsonArray = (JSONArray) studentObj.get(Student.FIELD_MARKS);
            for(int i=0; i < jsonArray.size(); i++){
                JSONObject markObj = (JSONObject) jsonArray.get(i);
                long mark = (long) markObj.get(Mark.FIELD_MARK);
                String subject = (String) markObj.get(Mark.FIELD_SUBJECT);
                marks.add(new Mark(mark, subject));
            }

            // Finally Student Object
            Student student = new Student(id, name, std, doj, marks);
            System.out.println("Student: ");
            System.out.println(student);

            // Teacher
            id = (String) teacherObj.get(Teacher.FIELD_ID);
            name = (String) teacherObj.get(Teacher.FIELD_NAME);
            doj = (String) teacherObj.get(Teacher.FIELD_DOJ);
            long salary = (long) teacherObj.get(Teacher.FIELD_SALARY);

            // Teacher classes
            ArrayList<String> classes = new ArrayList<>();
            jsonArray = (JSONArray) teacherObj.get(Teacher.FIELD_CLASSES);
            for(int i=0; i < jsonArray.size(); i++){
                String cls = (String) jsonArray.get(i);
                classes.add(cls);
            }

            // Finally Teacher object
            Teacher teacher = new Teacher(id, name, doj, salary, classes);
            System.out.println("Teacher: ");
            System.out.println(teacher);

        }catch(IOException e){
            System.err.println(e);
        }catch(ParseException e){
            System.err.println(e);
        }

    }
    
}
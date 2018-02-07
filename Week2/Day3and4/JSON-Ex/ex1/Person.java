package ex1;

/**
 * Person class
 */
public class Person {

    public static String FIELD_ID= "ID";
    public static String FIELD_NAME= "Name";
    public static String FIELD_DOJ= "Date Of Joining";

    protected String id;
    protected String name;
    protected String doj;

    Person(String id, String name, String doj){
        this.id = id;
        this.name = name;
        this.doj = doj;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfJoin() {
        return doj;
    }
    
}
package ex1;

/**
 * Mark class
 */
public class Mark {

    public static String FIELD_MARK= "Mark";
    public static String FIELD_SUBJECT= "Subject";

    private long mark;
    private String subject;

    Mark(long mark, String subject){
        this.mark = mark;
        this.subject = subject;
    }

    public void setMark(long mark) {
        this.mark = mark;
    }

    public long getMark() {
        return mark;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public String toString(){
        return subject+" -> "+mark;
    }

}
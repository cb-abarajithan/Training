package ex2;

/**
 * Week2 (Day1/2) - PhoneNumber class for a person.
 */
public class PhoneNumber {

    private long number;
    private Type type;

    PhoneNumber(long number, Type type){
        this.number = number;
        this.type = type;
    }

    public long getNumber() {
        return number;
    }

    public Type getType() {
        return type;
    }

    public String toString() {
        return String.format("%s: %d, ", type,number);
    }
    
    public static enum Type{
        MOBILE, HOME, WORK
    }
    
}
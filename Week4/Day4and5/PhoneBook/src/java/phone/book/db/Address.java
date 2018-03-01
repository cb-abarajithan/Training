package phone.book.db;

/**
 *
 * @author cb-abarajithan
 */
public class Address {
    
    private static final String DELIMITER = "$";
    
    private final String line1;
    private final String line2;
    private final String city;
    private final String state;
    private final int zip;
    private final String country;

    public Address(String line1, String line2, String city, String state, int zip, String country) {
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(line1).append(DELIMITER)
                .append(line2).append(DELIMITER)
                .append(city).append(DELIMITER)
                .append(state).append(DELIMITER)
                .append(zip).append(DELIMITER)
                .append(country).toString();
    }
    
}

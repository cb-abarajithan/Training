/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.service.db;

/**
 *
 * @author cb-abarajithan
 */
public class Address {
    
    private static final String DELIMITER = "$";
    
    private String line1;
    private String line2;
    private String city;
    private String state;
    private long zip;
    private String country;

    public Address(String line1, String line2, String city, long zip, String state, String country) {
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine2() {
        return line2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setZip(long zip) {
        this.zip = zip;
    }

    public long getZip() {
        return zip;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(line1.isEmpty()?"null":line1);
        builder.append(DELIMITER);
        builder.append(line2.isEmpty()?"null":line2);
        builder.append(DELIMITER);
        builder.append(city.isEmpty()?"null":city);
        builder.append(DELIMITER);
        builder.append(zip==0?"0":zip);
        builder.append(DELIMITER);
        builder.append(state.isEmpty()?"null":state);
        builder.append(DELIMITER);
        builder.append(country.isEmpty()?"null":country);
        builder.append(DELIMITER);
        return builder.toString();
    }
    
}

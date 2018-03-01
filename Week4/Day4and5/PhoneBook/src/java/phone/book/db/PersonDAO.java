package phone.book.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author cb-abarajithan
 */
public class PersonDAO {
    
    private static final String ADD_PERSON_QUERY = "INSERT INTO persons (fname, lname) VALUES (?, ?)";
    private static final String ADD_ADDR_QUERY = "INSERT INTO address VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String ADD_NUMBER_QUERY = "INSERT INTO phonenumber VALUES (?, ?, ?)";
    
    private static final String UPDATE_PERSON_QUERY = "UPDATE persons SET fname=?, lname=? WHERE id=?";
    private static final String UPDATE_ADDR_QUERY = "UPDATE address SET line1=?, line2=?, city=?, state=?, zip=?, country=? WHERE person_id=?";
    private static final String UPDATE_NUMBER_QUERY = "UPDATE phonenumber SET number=?, type=? WHERE person_id=?";
    
    private static final String GET_ALL_QUERY = "SELECT persons.*, address.*, ( GROUP_CONCAT(CONCAT(number, '(', type, ')') SEPARATOR ',') ) as numbers FROM persons LEFT JOIN address ON id=address.person_id LEFT JOIN phonenumber ON id=phonenumber.person_id GROUP BY id";
    private static final String GET_ALL_QUERY_BY_ID = "SELECT persons.*, address.*, ( GROUP_CONCAT(CONCAT(number, '(', type, ')') SEPARATOR ',') ) as numbers FROM persons LEFT JOIN address ON id=address.person_id LEFT JOIN phonenumber ON id=phonenumber.person_id WHERE id=? GROUP BY id";
    
    public static JSONObject getById(int id) throws SQLException{
        JSONObject obj = new JSONObject();
        
        PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(GET_ALL_QUERY_BY_ID);
        stmt.setInt(1, id);
        
        ResultSet set = stmt.executeQuery();
        
        String err = "Invalid Contact selected!";
        String fname="";
        String lname="";
        
        String line1="";
        String line2="";
        String city="";
        String state="";
        int zip=0;
        String country="";
        
        if(set.next()){
            fname = set.getString("fname");
            lname = set.getString("lname");

            line1 = set.getString("line1");
            line2 = set.getString("line2");
            city = set.getString("city");
            state = set.getString("state");
            zip = set.getInt("zip");
            country = set.getString("country");

            String[] numbers = set.getString("numbers").split(",");
            for(int i=0; i<3; i++){
                long no = Long.parseLong(numbers[i].substring(0, numbers[i].indexOf("(")));
                String key="";
                switch(i){
                    case 0:
                        key="mobile";
                        break;
                    case 1:
                        key="work";
                        break;
                    case 2:
                        key="home";
                        break;
                }
                obj.put(key, no);
            }
            err = "";
        }else{
            obj.put("mobile", "");
            obj.put("work", "");
            obj.put("home", "");
        }
        
        obj.put("err", err);
        obj.put("fname", fname);
        obj.put("lname", lname);

        obj.put("line1", line1);
        obj.put("line2", line2);
        obj.put("city", city);
        obj.put("state", state);
        obj.put("zip", zip);
        obj.put("country", country);
        
        return obj;
    }
    
    public static JSONArray getAll() throws SQLException{
        JSONArray persons = new JSONArray();
        
        Statement stmt = DatabaseConnection.getConnection().createStatement();
        ResultSet set = stmt.executeQuery(GET_ALL_QUERY);
        
        while(set.next()){
            
            JSONObject aPerson = new JSONObject();
            aPerson.put("id", set.getLong("id"));
            aPerson.put("fname", set.getString("fname"));
            aPerson.put("lname", set.getString("lname"));
            
            aPerson.put("line1", set.getString("line1"));
            aPerson.put("line2", set.getString("line2"));
            aPerson.put("city", set.getString("city"));
            aPerson.put("state", set.getString("state"));
            aPerson.put("zip", set.getInt("zip"));
            aPerson.put("country", set.getString("country"));
            
            String[] numbers = set.getString("numbers").split(",");
            for(int i=0; i<3; i++){
                long no = Long.parseLong(numbers[i].substring(0, numbers[i].indexOf("(")));
                String key="";
                switch(i){
                    case 0:
                        key="mobile";
                        break;
                    case 1:
                        key="work";
                        break;
                    case 2:
                        key="home";
                        break;
                }
                aPerson.put(key, no);
            }
            
            persons.add(aPerson);
        }
        
        return persons;
    }
    
    public static boolean updatePerson(int id, Person person) throws SQLException{
        PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(UPDATE_PERSON_QUERY);
        stmt.setString(1, person.getFirstName());
        stmt.setString(2, person.getLastName());
        stmt.setLong(3, id);
        
        int affected = stmt.executeUpdate();
        
        if(affected == 0){
            throw new SQLException("Cannot update contact!");
        }
        
        updateAddress(id, person.getAddress());
        for(int i=0; i<3; i++){
            updateNumber(id, person.getNumber(1));
        }
        return true;
        
    }
    
    private static void updateAddress(int id, Address addr) throws SQLException{
        PreparedStatement stmt = DatabaseConnection.getConnection().prepareCall(UPDATE_ADDR_QUERY);
        stmt.setString(1, addr.getLine1());
        stmt.setString(2, addr.getLine2());
        stmt.setString(3, addr.getCity());
        stmt.setString(4, addr.getState());
        stmt.setInt(5, addr.getZip());
        stmt.setString(6, addr.getCountry());
        stmt.setInt(7, id);
        
        int affected = stmt.executeUpdate();
        
        if(affected == 0){
            throw new SQLException("Cannot update address!");
        }
        
    }
    
    private static void updateNumber(int id, PhoneNumber num) throws SQLException{
        PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(UPDATE_NUMBER_QUERY);
        stmt.setLong(1, num.getNumber());
        stmt.setString(2, num.getType().toString().toLowerCase());
        stmt.setInt(3, id);
        
        int affected = stmt.executeUpdate();
        
        if(affected == 0){
            throw new SQLException("Cannot update "+num.getNumber()+"!");
        }
    }
    
    public static boolean addPerson(Person person) throws SQLException{
        PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(ADD_PERSON_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, person.getFirstName());
        stmt.setString(2, person.getLastName());
        
        boolean affected = stmt.execute();
        
        if(affected){
            throw new SQLException("Cannot add contact!");
        }
        
        try(ResultSet set = stmt.getGeneratedKeys()){
            if(set.next()){
                long id = set.getLong(1);
                insertAddress(id, person.getAddress());
                for(int i=0;i<3;i++){
                    insertNumber(id, person.getNumber(i));
                }
                return true;
            }else{
                throw new SQLException("Cannot add contact!");
            }
        }
        
    }

    private static void insertAddress(long id, Address addr) throws SQLException{
        PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(ADD_ADDR_QUERY);
        stmt.setLong(1, id);
        stmt.setString(2, addr.getLine1());
        stmt.setString(3, addr.getLine2());
        stmt.setString(4, addr.getCity());
        stmt.setString(5, addr.getState());
        stmt.setInt(6, addr.getZip());
        stmt.setString(7, addr.getCountry());
        
        int affected = stmt.executeUpdate();
        
        if(affected == 0){
            throw new SQLException("Failed to add address for contact!");
        }

    }
    
    private static void insertNumber(long id, PhoneNumber num) throws SQLException{
        PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(ADD_NUMBER_QUERY);
        stmt.setLong(1, id);
        stmt.setLong(2, num.getNumber());
        stmt.setString(3, num.getType().toString().toLowerCase());
        
        int affected = stmt.executeUpdate();
        
        if(affected == 0){
            throw new SQLException("Failed to add "+num.getNumber()+" for contact!");
        }
        
    }
    
}

package portal.service.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

public class UserDao {

    private static final String REGISTER_USER_QUERY = "INSERT INTO user (fname, lname, email, pass) VALUES('%s','%s','%s','%s')";
    private static final String REGISTER_ADDR_QUERY = "INSERT INTO address (user_id) VALUES(%d)";
    
    private static final String EMAIL_PASS_QUERY = "SELECT id FROM user WHERE email='%s' AND pass='%s'";
    private static final String GET_ID_QUERY = "SELECT id FROM user WHERE email='%s'";
    private static final String GET_USER_QUERY = "select fname, lname, email, line1, line2, city, zip, state, country from user inner join address on id=address.user_id WHERE email='%s'";
    
    private static final String UPDATE_QUERY_USER = "UPDATE user SET fname='%s', lname='%s' WHERE email='%s'";
    private static final String UPDATE_QUERY_ADDR = "UPDATE address SET line1='%s', line2='%s', city='%s', state='%s', zip=%d, country='%s' WHERE user_id=%d";
    
    private static final String DELETE_QUERY_USER = "DELETE FROM user WHERE email='%s'";
    private static final String DELETE_QUERY_ADDR = "DELETE FROM address WHERE user_id=%d";

    public static boolean register(String fname, String lname, String email, String pass) throws SQLException{
        if(execute(REGISTER_USER_QUERY, fname, lname, email, pass)){
            Statement stmt = DatabaseConnection.getConnection().createStatement();
            ResultSet idSet = stmt.executeQuery(String.format(GET_ID_QUERY, email));
            if(idSet.next()){
                long id = idSet.getInt(1);
                return execute(REGISTER_ADDR_QUERY, id);
            }
        }
        return false;
    }
    
    public static boolean isValid(String email, String pass) throws SQLException{
        Statement stmt = DatabaseConnection.getConnection().createStatement();
        ResultSet resultSet = stmt.executeQuery(String.format(EMAIL_PASS_QUERY, email, pass));
        return resultSet.next();
    }

    public static User getUserDetail(String email) throws SQLException{
        Statement stmt = DatabaseConnection.getConnection().createStatement();
        ResultSet resultSet = stmt.executeQuery(String.format(GET_USER_QUERY, email));
        User user = null;
        if(resultSet.next()){
            Address addr = new Address(resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getLong(7), resultSet.getString(8), resultSet.getString(9));
            user = new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), addr);
        }
        return user;
    }
    
    public static boolean updateDetails(String fname, String lname, String email, Address addr) throws SQLException{
        Statement stmt = DatabaseConnection.getConnection().createStatement();
        ResultSet idSet = stmt.executeQuery(String.format(GET_ID_QUERY, email));
        if(idSet.next() && execute(UPDATE_QUERY_USER, fname, lname, email)){
            long id = idSet.getInt(1);
            return execute(UPDATE_QUERY_ADDR, addr.getLine1(), addr.getLine2(),addr.getCity(),addr.getState(),addr.getZip(),addr.getCountry(), id);
        }
        return false;
    }
    
    public static boolean deactivateUser(String email) throws SQLException{
        Statement stmt = DatabaseConnection.getConnection().createStatement();
        ResultSet idSet = stmt.executeQuery(String.format(GET_ID_QUERY, email));
        if(idSet.next()){
            long id = idSet.getInt(1);
            return execute(DELETE_QUERY_ADDR, id) && execute(DELETE_QUERY_USER, email);
        }
        return false;
    }
    
    private static boolean execute(String query, Object... args) throws SQLException{
        Statement stmt = DatabaseConnection.getConnection().createStatement();
        int affected = stmt.executeUpdate(String.format(query, args));
        return (affected != 0);
    }
    
    public static boolean isEmailValid(String email){
        Pattern pattern = Pattern.compile("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$");
        return pattern.matcher(email).matches();
    }
    
}
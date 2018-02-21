package portal.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

    private static final String REGISTER_QUERY = "INSERT INTO user (fname, lname, email, pass) VALUES('%s','%s','%s','%s')";
    private static final String EMAIL_PASS_QUERY = "SELECT id FROM user WHERE email='%s' AND pass='%s'";
    private static final String GET_USER_QUERY = "SELECT fname, lname, address, email FROM user WHERE email='%s'";
    private static final String UPDATE_QUERY = "UPDATE user SET fname='%s', lname='%s', address='%s' WHERE email='%s'";
    private static final String DELETE_QUERY = "DELETE FROM user WHERE email='%s'";

    public static boolean register(String fname, String lname, String email, String pass) throws SQLException{
        return execute(REGISTER_QUERY, fname, lname, email, pass);
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
            user = new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
        }
        return user;
    }
    
    public static boolean updateDetails(String fname, String lname, String addr, String email) throws SQLException{
        return execute(UPDATE_QUERY, fname, lname, addr, email);
    }
    
    public static boolean deactivateUser(String email) throws SQLException{
        return execute(DELETE_QUERY, email);
    }
    
    private static boolean execute(String query, Object... args) throws SQLException{
        Statement stmt = DatabaseConnection.getConnection().createStatement();
        int affected = stmt.executeUpdate(String.format(query, args));
        return (affected != 0);
    }
    
}
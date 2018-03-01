/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phone.book.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author cb-abarajithan
 */
public class DatabaseConnection {
    
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/phonedirectory";

    private static final String USER = "root";
    private static final String PASS = "";

    private static Connection INSTANCE;

    public static Connection getConnection() throws SQLException {
        if(INSTANCE == null){
            try{
                Class.forName(JDBC_DRIVER);
            }catch(ClassNotFoundException e){
                throw new SQLException("Server database error!");
            }
            INSTANCE = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        return INSTANCE;
    }

    private DatabaseConnection(){}
    
}

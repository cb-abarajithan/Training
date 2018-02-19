import java.sql.*;

/**
 * Week3  (Day5) - PhoneDirectoryDatabase class to get a connection
 */
public class PhoneDirectoryDatabase {

    // Connection parameters
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private static final String DB_URL = "jdbc:mysql://localhost/PhoneDirectory";

    // Connection user parameters
    private static final String USER = "root";
    private static final String PASS = "";

    // SQL Queries
    private final String INSERT_PERSON_QUERY = "INSERT INTO persons (name, address) VALUES ('%s', '%s')";
    private final String INSERT_NUMBER_QUERY = "INSERT INTO phonenumber (person_id, number, type) VALUES (%d, %d, '%s')";

    private final String SELECT_ID_QUERY = "SELECT id FROM persons WHERE name='%s'";

    private final String UPDATE_NAME_QUERY = "UPDATE persons SET name='%s' WHERE name = '%s'";
    private final String UPDATE_NUMBER_QUERY = "UPDATE phonenumber SET number=%d WHERE number=%d";

    public static final String BASE_SELECT_QUERY = "SELECT name, address, "+
    "("+
    "GROUP_CONCAT(CONCAT(number, '(', type, ')' ) SEPARATOR ', ' ) "+
    ") as numbers "+
    "FROM persons LEFT JOIN phonenumber ON id = person_id GROUP BY id";

    public static final String SEARCH_NUMBER_QUERY = BASE_SELECT_QUERY + " HAVING phonenumber.number=%d";

    public static final String SEARCH_NAME_EXACT_QUERY = BASE_SELECT_QUERY + " HAVING name='%s'";

    public static final String SEARCH_NAME_QUERY = BASE_SELECT_QUERY + " HAVING name LIKE ";

    // Singleton instance
    private static PhoneDirectoryDatabase CONNECTION_INSTANCE;

    // Members
    private Connection conn;
    private Statement sqlStatement;

    static PhoneDirectoryDatabase getConnection() throws SQLException{
        if(CONNECTION_INSTANCE == null){
            try{
                Class.forName(JDBC_DRIVER);
            }catch(ClassNotFoundException e){
                System.out.println("SQL is not supported on your system.");
                System.exit(-1);
            }
            CONNECTION_INSTANCE = new PhoneDirectoryDatabase();        
        }
        return CONNECTION_INSTANCE;
    }

    private PhoneDirectoryDatabase() throws SQLException{
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        sqlStatement = conn.createStatement();
    }

    public long displayResult(String query) throws SQLException{
        long count=0;
        
        ResultSet resultSet = sqlStatement.executeQuery(query);
        while(resultSet.next()){
            if(resultSet.getString("name")!=null){
                System.out.printf("%s\t%s\t\t%s\n",
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("numbers"));
                count++;
            }
        }

        return count;
    }

    public void insertNew(String name, String address, long number, String type) throws SQLException{
        PreparedStatement prepStmt = conn.prepareStatement(String.format(INSERT_PERSON_QUERY, name, address), new String[] { "id" });
        prepStmt.execute();
        ResultSet resultSet = prepStmt.getGeneratedKeys();
        long insertedId = -1;
        if(resultSet.next()){
            insertedId = resultSet.getLong(1);
        }
        if(insertedId != -1){
            insertNumber(insertedId, number, type);
            System.out.println(name + " inserted successfully!\n");
        }else{
            throw new SQLException("Cannot add number for "+name);
        }
        prepStmt.close();
    }

    private void insertNumber(long id, long number, String type) throws SQLException{
        sqlStatement.execute(String.format(INSERT_NUMBER_QUERY, id, number, type));
    }

    public void insertNumberFrom(String name, long number, String type) throws SQLException{
        ResultSet resultSet = sqlStatement.executeQuery(String.format(SELECT_ID_QUERY, name));
        if(resultSet.next()){
            long id = resultSet.getLong(1);
            insertNumber(id, number, type);
            System.out.println(number + " added!");
        }else{
            System.out.println(name + " not found!");
        }
    }

    public void editName(String oldName, String newName) throws SQLException{
        int affected = sqlStatement.executeUpdate(String.format(UPDATE_NAME_QUERY, newName, oldName));
        if(affected != 0){
            System.out.println("Name changed from " + oldName + " to " + newName);
        }else{
            System.out.println("Cannot change name to " + newName);
        }
    }

    public void editNo(long oldNo, long newNo) throws SQLException{
        int affected = sqlStatement.executeUpdate(String.format(UPDATE_NUMBER_QUERY, newNo, oldNo));
        if(affected != 0){
            System.out.println("Number changed from " + oldNo + " to " + newNo);
        }else{
            System.out.println("Cannot change number to " + newNo);
        }
    }

    public void close() throws SQLException {
        sqlStatement.close();
        conn.close();
    }

}
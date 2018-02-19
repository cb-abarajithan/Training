import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.sql.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Week3  (Day5) -  DirectoryController class for PhoneDirectory.
 */
public class PhoneDirectoryController {

    private PhoneDirectoryDatabase db;

    PhoneDirectoryController() throws SQLException{
        this.db = PhoneDirectoryDatabase.getConnection();
    }

    private void printTitle(String title){
        System.out.println(title);
        System.out.println("Name\t\tAddress\t\t\t\tPhoneNumber(s)");
        System.out.println("------------------------------------------------------------------------------------------");
    }

    private void printFoundCount(long count){
        System.out.println("\nFound: " + count);
    }

    public void displayAll() throws SQLException{
        printTitle("\nAll Numbers: ");
        long count = db.displayResult(PhoneDirectoryDatabase.BASE_SELECT_QUERY);
        printFoundCount(count);
    }

    public void searchByNumber(long number) throws SQLException{
        printTitle("\nSearch Result: ");
        long count = db.displayResult(String.format(PhoneDirectoryDatabase.SEARCH_NUMBER_QUERY, number));
        printFoundCount(count);
    }

    public void searchByNameExact(String name)  throws SQLException{
        printTitle("\nSearch Result: ");
        long count = db.displayResult(String.format(PhoneDirectoryDatabase.SEARCH_NAME_EXACT_QUERY, name));
        printFoundCount(count);
    }

    public void searchByName(String name) throws SQLException{
        printTitle("\nSearch Result: ");
        long count = db.displayResult(PhoneDirectoryDatabase.SEARCH_NAME_QUERY + "'%" + name + "%'");
        printFoundCount(count);
    }

    public void insertNew(String name, String address, long number, String type) throws SQLException{
        db.insertNew(name, address, number, type);
    }

    public void insertNumber(String name, long number, String type) throws SQLException{
        db.insertNumberFrom(name, number, type);
    }

    public void editName(String oldName, String newName) throws SQLException{
        db.editName(oldName, newName);
    }

    public void editNo(long oldNo, long newNo) throws SQLException{
        db.editNo(oldNo, newNo);
    }

    public void close() throws SQLException {
        db.close();
    }

    public void readFromJSON() throws Exception{

        try(BufferedReader reader = new BufferedReader(new FileReader("persons.json"))){

            StringBuilder builder = new StringBuilder();
            String str;
            while((str = reader.readLine()) != null){
                builder.append(str);
            }
            // Json string
            str = builder.toString();

            JSONParser parser = new JSONParser();

            // Parse the string into JSONArray.
            JSONArray arrayObj = (JSONArray) parser.parse(str);

            // Size of persons json object
            int size = arrayObj.size();

            // Size of numbers json array a person has.
            int numbersSize;
            for(int i=0; i<size; i++){

                // Get the person json object
                JSONObject anObj = (JSONObject) arrayObj.get(i);

                // Parse details
                String name = (String) anObj.get("name");
                String address = (String) anObj.get("address");

                JSONArray numbersArray = (JSONArray) anObj.get("numbers");
                JSONObject firstNumObj = (JSONObject) numbersArray.get(0);

                // Parse number
                long number = (long) firstNumObj.get("number");

                // Parse type
                String type = (String) firstNumObj.get("type");

                db.insertNew(name, address, number, type);

                // Insert remaining numbers;
                numbersSize = numbersArray.size();
                for(int j=1; j<numbersSize; j++){

                    // Parse PhoneNumber json object
                    JSONObject numberObj = (JSONObject) numbersArray.get(j);

                    // Parse number
                    number = (long) numberObj.get("number");

                    // Parse type
                    type = (String) numberObj.get("type");

                    db.insertNumberFrom(name, number, type);

                }

            }

        }catch(FileNotFoundException e){
            System.out.println("persons.json no found!");
        }
    }
    
}
package ex2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Week2  (Day1/2) -  DirectoryController class for PhoneDirectory.
 */
public class PhoneDirectoryController {

    private ArrayList<Person> persons;

    PhoneDirectoryController(ArrayList<Person> persons){
        this.persons = persons;
    }

    private void printTitle(){
        System.out.println("Name\t\tAddress\t\t\t\tPhoneNumber(s)");
        System.out.println("------------------------------------------------------------------------------------------");
    }

    private void printFoundCount(long count){
        System.out.println("\nFound: " + count);
    }

    public void displayAll() {
        System.out.println("\nPhoneDirectory: ");
        printTitle();
        long count = persons.stream()
            .peek(System.out::println)
            .count();
        printFoundCount(count);
    }

    public void searchByNumber(long number) {
        System.out.println("Search Result:");
        printTitle();
        long count=0;
        for(Person p: persons){
            if(p.getPhoneNumbers().stream().anyMatch(ph -> ph.getNumber() == number)){
                System.out.println(p);
                count++;
            }
        }
        printFoundCount(count);
    }

    public void searchByNameExact(String name) {
        System.out.println("Search Result:");
        printTitle();
        long count = persons.stream()
            .filter(p -> p.getName().toLowerCase().contentEquals(name))
            .peek(System.out::println)
            .count();
        printFoundCount(count);
    }

    public void searchByName(String name) {
        System.out.println("Search Result:");
        printTitle();
        long count = persons.stream()
            .filter(p -> p.getName().toLowerCase().contains(name))
            .peek(System.out::println)
            .count();
        printFoundCount(count);
    }

    public static ArrayList<Person> getSampleData() {
        ArrayList<Person> sampleData = new ArrayList<>();

        Person sample = new Person("BruceWayne", "Waynemanner, Gotham", new PhoneNumber(345654647, PhoneNumber.Type.HOME));
        sampleData.add(sample);

        sample = new Person("ClarkKent", "Farmhouse, Metropolis", new PhoneNumber(45674745, PhoneNumber.Type.MOBILE));
        sample.addNumber(new PhoneNumber(39835983, PhoneNumber.Type.WORK));
        sample.addNumber(new PhoneNumber(29385643, PhoneNumber.Type.HOME));
        sampleData.add(sample);

        sample = new Person("DianaPrince", "Themaskeera, Atlanta", new PhoneNumber(586564357, PhoneNumber.Type.MOBILE));
        sample.addNumber(new PhoneNumber(34897573, PhoneNumber.Type.WORK));
        sampleData.add(sample);

        sample = new Person("BruceBanner", "Some Streets, India", new PhoneNumber(87634786, PhoneNumber.Type.MOBILE));
        sample.addNumber(new PhoneNumber(94895784, PhoneNumber.Type.HOME));
        sampleData.add(sample);

        sample = new Person("TonyStark", "BeachHouse, California", new PhoneNumber(857684598, PhoneNumber.Type.HOME));
        sampleData.add(sample);

        sample = new Person("GodamnBatman", "The Batcave, Gotham", new PhoneNumber(82735623, PhoneNumber.Type.MOBILE));
        sample.addNumber(new PhoneNumber(948475673, PhoneNumber.Type.WORK));
        sampleData.add(sample);

        return sampleData;
    }

    public static ArrayList<Person> readFromJSON(String filename) throws ParseException{

        ArrayList<Person> persons = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){

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

                // Person java object.
                Person person = new Person(name, address);

                JSONArray numbersArray = (JSONArray) anObj.get("numbers");

                numbersSize = numbersArray.size();
                for(int j=0; j<numbersSize; j++){

                    // Parse PhoneNumber json object
                    JSONObject numberObj = (JSONObject) numbersArray.get(j);

                    // Parse number
                    long number = (long) numberObj.get("number");

                    // Parse type
                    String numType = (String) numberObj.get("type");
                    PhoneNumber.Type type;
                    if(numType.contentEquals(PhoneNumber.Type.HOME.toString())){
                        type = PhoneNumber.Type.HOME;
                    }else if(numType.contentEquals(PhoneNumber.Type.MOBILE.toString())){
                        type = PhoneNumber.Type.MOBILE;
                    }else{
                        type = PhoneNumber.Type.WORK;
                    }

                    person.addNumber(new PhoneNumber(number, type));

                }

                persons.add(person);

            }

        }catch(IOException e){
            System.out.println(e);
        }

        return persons;

    }
    
}
package ex2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;

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

    public static ArrayList<Person> readFromCSV(String filename) throws IOException{

        ArrayList<Person> persons = new ArrayList<>();

        FileReader reader = new FileReader(filename);
        CSVParser parser = CSVParser.parse(reader, CSVFormat.DEFAULT);

        int column;
        int size;
        for(CSVRecord record: parser){

            // Get the name and address first.
            String name = record.get(0);
            String address = record.get(1);
            Person person = new Person(name, address);

            // PhoneNumbers always start with column 2.
            column = 2;
            size = record.size();

            // A person can have multiple phone numbers.
            while(column < size){
                // Get a PhoneNumber string
                String phoneNumberString = record.get(column++);
                // Split them.
                String[] splitString = phoneNumberString.split("-");
                // Get the Number
                long number = Long.parseLong(splitString[0]);
                // Get the Type.
                PhoneNumber.Type type;
                if(splitString[1].contentEquals(PhoneNumber.Type.HOME.toString())){
                    type = PhoneNumber.Type.HOME;
                }else if(splitString[1].contentEquals(PhoneNumber.Type.MOBILE.toString())){
                    type = PhoneNumber.Type.MOBILE;
                }else{
                    type = PhoneNumber.Type.WORK;
                }
                // Add the PhoneNumber to that person.
                person.addNumber(new PhoneNumber(number, type));
            }

            // Add that person to the list.
            persons.add(person);

        }

        return persons;

    }
    
}
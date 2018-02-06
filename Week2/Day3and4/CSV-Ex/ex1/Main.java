package ex1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * Week2 (Day3/4) - Program to output a new CSV file with all the duplicate lines removed.
 */
public class Main {

    public static void main(String[] args) {
        
        if(args.length < 1){
            System.out.println("usage: java Main <csv_file>");
            System.exit(-1);
        }

        Set<String> recordSet = new HashSet<>();

        FileReader reader = null;
        CSVParser parser = null;
        FileWriter writer = null;
        CSVPrinter printer = null;

        try {

            reader = new FileReader(args[0]);
            parser = CSVParser.parse(reader, CSVFormat.DEFAULT);

            writer = new FileWriter("ex1_output.csv");
            printer = new CSVPrinter(writer, CSVFormat.DEFAULT);

            StringBuilder builder;
            for (CSVRecord record : parser) {
                builder = new StringBuilder();
                for(String value: record){
                    builder.append(value);
                }
                String justString = builder.toString();
                if(!recordSet.contains(justString))
                    printer.printRecord(record);
                recordSet.add(justString);
            }

        } catch (IOException e) {
            System.out.println(e);
        }finally{
            if(parser != null){
                try {
                    parser.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
            if(printer != null){
                try {
                    printer.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }

    }
    
}
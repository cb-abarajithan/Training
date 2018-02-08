import java.io.FileNotFoundException;
import java.io.IOException;

import app.csv.CSVConverter;
import app.csv.CSVFile;
import app.json.Config;

/**
 * Main class
 */
public class Main {

    public static void main(String[] args) {
        
        if(args.length < 1){
            System.out.println("usage: java Main <input_csv>");
            System.exit(-1);
        }

        try{
            Config configuration = Config.readConfig();
            CSVFile file = new CSVFile(args[0]);
            CSVConverter converter = new CSVConverter(configuration, file);
            converter.convert();
        }catch(IOException e){
            System.out.println(e);
        }

    }
    
}
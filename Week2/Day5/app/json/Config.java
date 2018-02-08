package app.json;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import app.json.ConfigItem;
import app.exception.MalformedFileException;

/**
 * Config class holding all configurations of config.json.
 */
public class Config {

    private ConfigItem[] configs;
    private int size;

    private Config(JSONArray configJsonArray)throws IOException{
        size = configJsonArray.size();
        this.configs = new ConfigItem[size];
        for(int i=0; i<size; i++){
            configs[i] = new ConfigItem((JSONObject) configJsonArray.get(i));
        }
    }

    public ConfigItem[] getConfigs() {
        return configs;
    }

    public int getSize() {
        return size;
    }

    public static Config readConfig() throws IOException{

        // Init str with empty array
        String str = "[]";

        // BufferedReader to read the config.json
        try(BufferedReader reader = new BufferedReader(new FileReader("config.json"))){

            // Reading json string
            StringBuilder builder = new StringBuilder();
            while((str = reader.readLine()) != null){
                builder.append(str);
            }
            str = builder.toString();

        }catch(FileNotFoundException e){
            throw new IOException("config.json file is missing.");
        }catch(IOException e){
            throw new IOException(e.getMessage());
        }
        
        // Return a new ConfigReader instance.
        return new Config((JSONArray) JSONValue.parse(str));
    }
    
}
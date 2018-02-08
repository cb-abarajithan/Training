package app.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import app.csv.CSVFile;
import app.csv.CSVHeader;
import app.json.Config;
import app.json.ConfigItem;

import org.json.simple.JSONObject;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVFormat;

/**
 * Converting a CSV file from one format to another 
 * in accordance with a config file.
 */
public class CSVConverter {

    private Config config;
    private CSVFile file;

    public CSVConverter(Config config, CSVFile file){
        this.config = config;
        this.file = file;
    }

    public void convert() throws IOException{
        
        ArrayList<String> newHeaders = prepareHeader();
        ArrayList<ArrayList<String>> newRecords = prepareRecords();

        CSVPrinter printer = new CSVPrinter(new FileWriter("output.csv"), CSVFormat.DEFAULT);
        printer.printRecord(newHeaders);
        printer.printRecords(newRecords);

        printer.close();

    }

    private ArrayList<ArrayList<String>> prepareRecords(){

        ArrayList<ArrayList<String>> newRecords = new ArrayList<>();

        for(ConfigItem item: config.getConfigs()){

            for(int recIndex=0; recIndex<file.getRecordsSize()-1; recIndex++){

                ArrayList<String> aRecordList = new ArrayList<>();

                // Filter records
                for(String header: file.getHeaders()){
                    if(!Arrays.stream(item.getOldHeaders()).anyMatch(s -> header.contentEquals(s))){
                        aRecordList.add(file.getRecordsMap().get(header).get(recIndex));
                    }
                }

                // Get the header size from a config item.
                int groupableRecSize = item.getHeadersSize();

                // Get the records that are to be grouped as JSON.
                String[] groupingRecords = new String[groupableRecSize];
                for(int j=0; j<groupableRecSize; j++){
                    groupingRecords[j] = file.getRecordsMap().get(item.getOldHeader(j)).get(recIndex);
                }

                // Get the JSON String.
                String jsonRecord = prepareJsonRecord(item, groupingRecords);

                // Add that JSON value
                long pos = item.getPosition();
                if(pos < 0 || pos > file.getSize()){
                    aRecordList.add(jsonRecord);
                }else{
                    aRecordList.add((int) pos, jsonRecord);
                }

                newRecords.add(aRecordList);

            }

        }
        return newRecords;

    }

    private String prepareJsonRecord(ConfigItem item, String... groupingRecords){

        JSONObject jsonObj = new JSONObject();

        for(int i=0; i<groupingRecords.length; i++){
            jsonObj.put(item.getNewHeader(i), groupingRecords[i]);
        }

        return jsonObj.toJSONString();

    }

    private ArrayList<String> prepareHeader(){
        ArrayList<String> newHeaders = new ArrayList<>();

        for(ConfigItem item: config.getConfigs()){
            // Filters the headers first.
            for(String header: file.getHeaders()){
                if(!Arrays.stream(item.getOldHeaders()).anyMatch(s -> header.contentEquals(s))){
                    newHeaders.add(header);
                }
            }
            // Add the new header.
            long pos = item.getPosition();
            if(pos < 0 || pos > file.getSize()){
                newHeaders.add(item.getGroupHeader());
            }else{
                newHeaders.add((int) pos, item.getGroupHeader());
            }
        }

        return newHeaders;
    }
    
}
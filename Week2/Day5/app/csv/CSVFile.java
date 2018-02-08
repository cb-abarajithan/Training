package app.csv;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.csv.CSVHeader;
import app.exception.MalformedFileException;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;

/**
 * 
 * Contains all details of the CSV File.
 * 
 * constructor internally invokes parseFile()
 * 
 */
public class CSVFile {

    private Map<String, ArrayList<String>> recordsMap;
    private String[] headers;

    private int size;
    private long recordsSize;

    public CSVFile(String filename) throws IOException{
        parseFile(filename);
    }

    private void parseFile(String filename) throws IOException{
        CSVParser parser = CSVParser.parse(new FileReader(filename), CSVFormat.DEFAULT);
        List<CSVRecord> allRecords = parser.getRecords();
        this.recordsSize = allRecords.size();
        validate(allRecords.remove(0),allRecords);
    }

    private void validate(CSVRecord header, List<CSVRecord> allRecords) throws MalformedFileException{
        size = header.size();
        for(CSVRecord record: allRecords){
            if(size != record.size()){
                throw new MalformedFileException("CSV File is Malformed: Not all records have same size.");
            }
        }
        loadRecords(header, allRecords);
        loadHeaders(header);
    }

    private void loadRecords(CSVRecord header, List<CSVRecord> allRecords){
        this.recordsMap = new HashMap<>();
        for(int i=0; i<size;i++){
            for(CSVRecord aRecord: allRecords){
                ArrayList<String> colRecords = recordsMap.get(header.get(i));
                if(colRecords==null){
                    colRecords = new ArrayList<>();
                }
                colRecords.add(aRecord.get(i));
                recordsMap.put(header.get(i), colRecords);
            }
        }
    }

    private void loadHeaders(CSVRecord header){
        int size = header.size();
        this.headers = new String[size];
        for(int i=0; i<size; i++){
            this.headers[i] = header.get(i);
        }
    }

    public String[] getHeaders() {
        return headers;
    }

    public Map<String, ArrayList<String>> getRecordsMap() {
        return recordsMap;
    }

    public int getSize() {
        return size;
    }

    public long getRecordsSize() {
        return recordsSize;
    }
    
}
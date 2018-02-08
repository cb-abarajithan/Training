package app.json;

import app.exception.MalformedFileException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/**
 * ConfigItem class holding a single object's details from 'config.json'
 */
public class ConfigItem {

    public static final String FIELD_GROUP_HEADER = "group_header";
    public static final String FIELD_OLD = "old";
    public static final String FIELD_NEW = "new";
    public static final String FIELD_POSITION = "position";

    private String groupHeader;
    private String[] oldHeaders;
    private String[] newHeaders;
    private long position;

    private int size;

    ConfigItem(JSONObject configJson) throws MalformedFileException{
        this.groupHeader = (String) configJson.get(FIELD_GROUP_HEADER);
        this.position = (long) configJson.get(FIELD_POSITION);
        validate((JSONArray) configJson.get(FIELD_OLD), (JSONArray) configJson.get(FIELD_NEW));
    }

    private void validate(JSONArray oldArray,JSONArray newArray)throws MalformedFileException{
        if(oldArray.size() != newArray.size()){
            throw new MalformedFileException("Size of old headers must be equal to new headers in config.json");
        }else{
            initOldHeaders(oldArray);
            initNewHeaders(newArray);
        }
    }

    private void initOldHeaders(JSONArray headersArray){
        size = headersArray.size();
        this.oldHeaders = new String[size];
        for(int i=0; i<size; i++){
            oldHeaders[i] = (String) headersArray.get(i);
        }
    }

    private void initNewHeaders(JSONArray headersArray){
        this.newHeaders = new String[size];
        for(int i=0; i<size; i++){
            newHeaders[i] = (String) headersArray.get(i);
        }
    }

    public String getGroupHeader() {
        return groupHeader;
    }

    public String[] getOldHeaders() {
        return oldHeaders;
    }

    public String getOldHeader(int index){
        return oldHeaders[index];
    }

    public String[] getNewHeaders() {
        return newHeaders;
    }

    public String getNewHeader(int index){
        return newHeaders[index];
    }

    public int getHeadersSize() {
        return size;
    }

    public long getPosition() {
        return position;
    }
    
}
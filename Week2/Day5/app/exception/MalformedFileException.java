package app.exception;

import java.io.IOException;

/**
 * Thrown when the config.json is malformed.
 * 
 * Eg: Incorrect 
 * 
 */
public class MalformedFileException extends IOException{

    public MalformedFileException(String msg){
        super(msg);
    }
    
}
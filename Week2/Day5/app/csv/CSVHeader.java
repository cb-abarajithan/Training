package app.csv;

/**
 * Holds the name and the position of the header of the input csv file.
 */
public class CSVHeader {

    private String headerName;
    private int pos;

    public CSVHeader(String headerName, int pos){
        this.headerName = headerName;
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public String getHeaderName() {
        return headerName;
    }
    
}
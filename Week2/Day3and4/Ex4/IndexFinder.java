import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Week2 (Day3/4) - IndexFinder class finds all the indices of a search keyword in a given string.
 */
public class IndexFinder {

    private Pattern pattern;
    private Matcher matcher;

    private BufferedReader reader;
    private PrintWriter writer;

    public IndexFinder(String filename, String searchKey) throws FileNotFoundException{
        this.pattern = Pattern.compile("\\b"+searchKey+"\\b");
        this.reader = new BufferedReader(new FileReader(filename));
        this.writer = new PrintWriter(searchKey+".locations");
    }

    public void startSearch() throws IOException{

        int lineNo = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            LineIndexPair pair = findIndices(++lineNo, line);
            if(pair != null){
                writer.println(pair);
            }
        }

    }

    public LineIndexPair findIndices(int lineNo, String line){

        LineIndexPair pair = new LineIndexPair(lineNo);
        this.matcher = pattern.matcher(line);

        while(matcher.find()){
            pair.add(matcher.start());
        }

        if(pair.isEmpty()) return null;

        return pair;
    }

    public void close() throws IOException{
        reader.close();
        writer.close();
    }
    
}
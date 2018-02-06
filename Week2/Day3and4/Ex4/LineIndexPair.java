import java.util.ArrayList;

/**
 * Week2 (Day3/4) -  LineIndexPair holds line number and List of indices for that line.
 */
public class LineIndexPair {

    private int lineNo;
    private ArrayList<Integer> indices;

    public LineIndexPair(int lineNo){
        this.lineNo = lineNo;
        indices = new ArrayList<>();
    }

    public LineIndexPair add(int index){
        indices.add(index);
        return this;
    }

    public boolean isEmpty(){
        return indices.size() == 0;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(lineNo + ": ");
        for (Integer i : indices) {
            builder.append(i + ", ");
        }
        return builder.toString();
    }
    
}
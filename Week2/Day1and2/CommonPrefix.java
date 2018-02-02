import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Week2 (Day1/2) -  CommonPrefix Exercise 2
 */
public class CommonPrefix {

    public static void main(String[] args) {
        
        String[] words = Util.getWords();

        Map<String,ArrayList<String>> prefixMap = new HashMap<>();
        for (String word : words) {
            
            String sub = word.substring(0, 3);
            ArrayList<String> firstThreeList = prefixMap.get(sub);
            if(firstThreeList==null){
                firstThreeList = new ArrayList<>();
            }
            firstThreeList.add(word);
            prefixMap.put(sub, firstThreeList);

        }

        prefixMap.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach( e -> System.out.println(e.getKey()+" -> "+e.getValue()) );

    }
    
}
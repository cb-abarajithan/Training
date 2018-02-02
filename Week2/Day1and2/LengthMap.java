import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Week2 (Day1/2) - LengthMap
 */
public class LengthMap {

    public static void main(String[] args) {
        
        String[] words = Util.getWords();

        Map<Integer,ArrayList<String>> lengthWordMap = new HashMap<>();

        System.out.print("Mapping words... ");
        for (String word : words) {
            int len = word.length();
            ArrayList<String> wordlist = lengthWordMap.get(len);
            if(wordlist==null){
                wordlist = new ArrayList<>();
            }
            wordlist.add(word);
            lengthWordMap.put(len, wordlist);
        }

        System.out.println("Done");

        lengthWordMap.entrySet()
                    .stream()
                    .forEach( e -> System.out.println(e.getKey()+" -> "+e.getValue()) );

    }
    
}
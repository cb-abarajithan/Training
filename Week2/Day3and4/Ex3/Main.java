import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * Week2 (Day3/4) - program which reads a text file. 
 *                  List all the words in alphabetical order with number of occurrence for each word and 
 *                  write the list into another text file in the format [word]:[number of times it occurred].
 */
public class Main {

    public static void main(String[] args) {
        
        if(args.length < 1){
            System.out.println("usage: java Main <input-file.txt>");
            System.exit(-1);
        }

        BufferedReader reader = null;
        PrintWriter writer = null;

        try{
            reader = new BufferedReader(new FileReader(args[0]));
            writer = new PrintWriter("output.txt");

            // Read from text file.
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            
            // Sort the words and copy to a list.
            String[] words = builder.toString().split(" ");
            List<String> sortedWords = Arrays.stream(words)
                .sorted()
                .collect(Collectors.toList());

            Map<String,Integer> wordMap = new HashMap<>();
            for(String word: sortedWords){
                int count = wordMap.getOrDefault(word, 0);
                wordMap.put(word, ++count);
            }

            for(Entry<String,Integer> e: wordMap.entrySet()){
                System.out.printf("%s: %d%n",e.getKey(),e.getValue());
                writer.printf("%s: %d%n",e.getKey(),e.getValue());
            }

        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader!=null){
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(writer!=null){
                writer.close();
            }
        }

    }
    
}
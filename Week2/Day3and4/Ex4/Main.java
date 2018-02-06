import java.io.IOException;

/**
 * Week2 (Day3/4) - Main class to search a word in a file and write its line number and their starting indices. 
 */
public class Main {

    public static void main(String[] args) {
        
        if(args.length < 2){
            System.out.println("usage: java Main <file_name> <search_term>");
            System.exit(-1);
        }

        IndexFinder finder = null;

        try{
            finder = new IndexFinder(args[0], args[1]);
            finder.startSearch();
        }catch(IOException e){
            System.err.println(e);
        }finally{
            
            if(finder != null){
                try{
                    finder.close();
                }catch(IOException e){
                    System.err.println(e);
                }
            }
            
        }

    }
    
}
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

/**
 * Week2 (Day3/4) - Ex2: Scan dirs and its sub dirs and move all the files into a separate single directory.
 * If a file with the same name is already present, the file name should be appended with the incremented number.
 * 
 * Eg: a.txt, a-1.txt, a-2.txt ...
 * 
 */
public class Ex2{
    
    public static void main(String[] args) {

        if(args.length < 1){
            System.out.println("usage: java Ex2 <input_dir>");
            System.exit(-1);
        }

        try{
            Path sourcePath = Paths.get(args[0]);

            SingleDestinationFileMover fileMover = new SingleDestinationFileMover();
            Files.walkFileTree(sourcePath, fileMover);
        }catch(IOException e){
            System.err.format("%s doesn\'t exists%n", args[0]);
        }

    }
    
}
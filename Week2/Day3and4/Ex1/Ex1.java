import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.FileVisitResult.*;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Week 2 (Day3/4) - Ex1: Scan dirs and its sub dirs and print count of the files with same extension.
 */
public class Ex1 extends SimpleFileVisitor<Path>{

    private Map<String, Integer> filesMap;

    Ex1(){
        this.filesMap = new HashMap<>();
    }

    public FileVisitResult visitFile(Path file, BasicFileAttributes attr){
        String ext = getExtension(file.getFileName().toString());
        Integer count = filesMap.getOrDefault(ext, 0);
        filesMap.put(ext, ++count);
        return CONTINUE;
    }

    private String getExtension(String filename){
        return filename.substring(filename.indexOf('.')+1);
    }

    public void done(){
        filesMap.entrySet().stream().forEach( e -> {
            System.out.printf("%s -> %d\n",e.getKey(),e.getValue());
        });
    }
    
    public static void main(String[] args){
        
        if(args.length < 1){
            System.out.println("\nusage: java Ex1 <dir_path>");
            System.out.println("example: java Ex1 ../../Week1");
            System.exit(-1);
        }

        try{
            Ex1 ex = new Ex1();
            Files.walkFileTree(Paths.get(args[0]), ex);
            ex.done();
        }catch(IOException e){
            System.err.println(e);
        }

    }
    
}
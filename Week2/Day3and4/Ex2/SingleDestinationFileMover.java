import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *                  Helps to move the contents of a directory and its subdirectory to 
 * Week2 (Day3/4) - a single destination directory.
 *                  With Incremental file names.
 */
public class SingleDestinationFileMover extends SimpleFileVisitor<Path>{

    private Path destPath;
    private MoveFilesHelper moveHelper;

    SingleDestinationFileMover(){
        this.destPath = Paths.get("test-output");
        initDirectory();
        this.moveHelper = new MoveFilesHelper(destPath);
    }

    private void initDirectory(){
        if(Files.notExists(destPath)){
            try{
                Files.createDirectory(destPath);
            }catch(IOException e){
                System.err.println("Unable to create output directory!");
                System.exit(-1);
            }
        }
    }

    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        try{
            move(file);
        }catch(IOException e){
            System.err.format("** Unable to move file: %s **%n",file);
        }
        return FileVisitResult.CONTINUE;
    }

    private void move(Path file) throws IOException{

        Path target = moveHelper.getIncrementedName( file.getFileName() );
        Files.move(file, target, StandardCopyOption.ATOMIC_MOVE);

    }

    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        
        try{
            Files.delete(dir);
        }catch (NoSuchFileException x) {
            System.err.format("** %s: No such file or directory **%n",dir);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("** %s not empty **%n", dir);
        }catch(IOException e){
            System.err.format("** Unable to delete file: %s **%n",dir);
        }
        return FileVisitResult.CONTINUE;

    }
}
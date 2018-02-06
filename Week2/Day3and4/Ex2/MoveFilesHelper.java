import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Week2 (Day2/3) - MoveFilesHelper class helps to check if destination folder contains
 *                  file of same name from source folder.
 */
public class MoveFilesHelper {

    private Path destination;
    private int counter;

    MoveFilesHelper(Path destination){
        this.destination = destination;
    }

    private boolean exists(Path filename){
        Path destWithFile = Paths.get(destination.toString(), filename.toString());
        return Files.exists(destWithFile);
    }

    private String getExtension(String fileString){
        return fileString.substring(fileString.indexOf('.')+1);
    }

    public Path getIncrementedName(Path filename){
        if(!exists(filename)){
            counter = 0;
            return destination.resolve(filename);
        }
        // Get path as String.
        String fileString = filename.toString();

        // Omitting file extension.
        String origName = fileString.substring(0, fileString.indexOf('.'));

        // Get index of '-'
        int hifenIndex = fileString.indexOf('-');

        if(hifenIndex > 0){
            // File name that is already renamed exists. (ie) contains '-'
            // Get the counter value.
            counter = Integer.parseInt(fileString.substring(hifenIndex+1, fileString.indexOf('.')) );

            // Reset the original name
            origName = fileString.substring(0, hifenIndex);
        }
        return getIncrementedName( Paths.get(origName + "-" + (++counter) + "." + getExtension(fileString)) );
    }
    
}
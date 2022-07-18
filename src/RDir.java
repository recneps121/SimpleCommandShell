import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class RDir {

    public RDir(String[] command) {
        String dirName = "";

        //Parses the path name from the command.
        if (command.length > 1) {
            dirName = command[1];
        } else {
            System.out.println("Must supply Directory name.");
        }

        //This removes the directory.
        Path dirPath = FileSystems.getDefault().getPath(dirName);
        File newDir = new File(dirPath.toString());

        if (newDir.delete()) {
            System.out.print("");
        } else {
            System.out.println("Invalid path");
        }
    }
}

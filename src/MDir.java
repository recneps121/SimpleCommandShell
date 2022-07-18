import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * This process makes a directory when mdir (directory_name)
 * is called. Everything is in relation to "./" so make sure to
 * call your paths as such.
 */
public class MDir {

    public MDir(String[] command) {
        String dirName = "";

        //Parses the path name from the command.
        if (command.length > 1) {
            dirName = command[1];
        } else {
            System.out.println("Must supply Directory name.");
        }

        //This makes the directory.
        Path dirPath = FileSystems.getDefault().getPath(dirName);
        File newDir = new File(dirPath.toString());

        if (newDir.mkdir()) {
            System.out.print("");
        } else {
            System.out.println("Invalid path");
        }
    }
}

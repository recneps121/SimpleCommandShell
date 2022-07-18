import java.io.File;
import java.io.IOException;

/**
 * This process changes the working directory of the shell.
 * One sidenote. Everything is in relation to "./" and the paths entered need to be as such.
 * Just cd will go to home.
 */
public class ChangeDirectory {

    public ChangeDirectory(String[] command) {
        String path;

        //Extracts the path from the command.
        if (command.length > 1) {
            path = command[1];
            System.out.println(path);
        } else {
            path = System.getProperty("user.home");
        }

        try {
            //Changes the directory.
            File directory = new File(path).getCanonicalFile();
            System.out.println(directory);
            if (directory.exists())
            {
                System.setProperty("user.dir", directory.getCanonicalPath());
            } else {
                System.out.println("Invalid Path");
            }
        } catch (IOException ioException) {
            System.out.println("Invalid Path");
        }
    }
}

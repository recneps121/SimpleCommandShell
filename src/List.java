import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This program is for the List command. It operates much like unto
 * "ls -l" in Linux. However, it doesn't take any arguments.
 */
public class List {

    public List() {
        //This gets all the files in the current directory.
        File dir = new File(System.getProperty("user.dir")).getAbsoluteFile();
        File[] directoryListing = dir.listFiles();

        if (directoryListing != null) {
            //For each file in the directory, it generates a string to print in the list.
            for (File child : directoryListing) {
                //These are all the attributes you will find in the ls -l program, and this program
                //picks and chooses which ones the file is.
                String attributes = "";
                if (child.isDirectory()) {
                    attributes += "d";
                } else {
                    attributes += "-";
                }

                if (child.canRead()) {
                    attributes += "r";
                } else {
                    attributes += "-";
                }

                if (child.canWrite()) {
                    attributes += "w";
                } else {
                    attributes += "-";
                }

                if (child.canExecute()) {
                    attributes += "x";
                } else {
                    attributes += "-";
                }

                attributes += String.format("%10d  ", child.length());

                DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm  ");
                Date date = new Date(child.lastModified());

                attributes += String.format("%21s", dateFormat.format(date));
                attributes += child.getName();

                System.out.println(attributes);
                
            }
        } else {
            System.out.println("Error: Invalid Directory");
        }
    }
}

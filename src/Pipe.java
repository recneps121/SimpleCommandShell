import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * This program executes any external command that involves
 * the piping of two commands together.
 */
public class Pipe {

    public Pipe(String[] command, PTime pTime) {
        //Declares space for the two seperate commands.
        String[] firstCommand = new String[Arrays.asList(command).indexOf("|")];
        String[] secondCommand = new String[command.length - 1 - Arrays.asList(command).indexOf("|")];

        //Parses the two commands into individual arrays.
        System.arraycopy(command, 0, firstCommand, 0, firstCommand.length);
        System.arraycopy(command, Arrays.asList(command).indexOf("|") + 1, secondCommand, 0, secondCommand.length);
        ProcessBuilder[] commands = {
                new ProcessBuilder(firstCommand).directory(new File(System.getProperty("user.dir"))),
                new ProcessBuilder(secondCommand).directory(new File(System.getProperty("user.dir")))
        };

        try {
            //This pipes together the two programs and runs them.
            pTime.start();
            List<Process> processes = ProcessBuilder.startPipeline(Arrays.asList(commands));
            Process lastCommand = processes.get(processes.size() - 1);

            InputStream stream = lastCommand.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String output;

            //This prints out the output for the last command.
            while ((output = reader.readLine()) != null) {
                System.out.println(output);
            }
            pTime.stop();

        } catch (IOException ioException) {
            System.out.println("Invalid Command");
        }
    }
}

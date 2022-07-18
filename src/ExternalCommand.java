import java.io.*;
import java.util.Arrays;

/**
 * This program executes any command not built into our shell,
 * and calls the Pipe process if the command involves piping.
 */
public class ExternalCommand {

    public ExternalCommand(String[] command, PTime pTime) {
        Boolean noWait = false;

        try {
            if (command[command.length - 1].equals("&")) {
                noWait = true;
            }
            //If the command is actually two commands on a pipeline execute the Pipe program.
            if (Arrays.asList(command).contains("|")) {
                Pipe pipe = new Pipe(command, pTime);
            } else {
                //This implementation of feeding the output to the console
                // is an adaptation of code taken from https://www.java-forums.org/new-java/86025-processbulider-output-console-print.html

                //This creates an object of the process and runs it.
                ProcessBuilder externalCommand = new ProcessBuilder(command);
                externalCommand.directory(new File(System.getProperty("user.dir")));
                pTime.start();
                Process externalProcess = externalCommand.start();

                //This is where the program pipes and prints the output into our shell.
                InputStream stream = externalProcess.getInputStream();
                if (!noWait) {
                    externalProcess.waitFor();
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String output;

                while ((output = reader.readLine()) != null) {
                    System.out.println(output);
                }

                pTime.stop();
            }
        } catch (IOException ioException) {
            System.out.println("Invalid Command");
        } catch (InterruptedException interruptedException) {
            System.out.println("Process " + command.toString() + " was interrupted.");
        }
    }

}

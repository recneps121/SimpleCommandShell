//Name of Creator: Spencer Ryan Hall
//Name of Project: Assignment 3 - Command Shell
//Contact of Creator: spencer@hallblog.com

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Welcome to the Shell. This is the main class of the program.
 * This is where the program decides what to do with you input
 * and executes the command appropriate.
 */
public class Shell {
    private boolean active = true;


    public static void main(String[] args) {
        Shell shell = new Shell();
        PTime pTime = new PTime();
        Scanner prompt = new Scanner(System.in);
        History history = new History();

        //Until you hit exit or the program is terminated. She is gonna keep on running.
        while (shell.getActive()) {
            //This creates the prompt for you to type your input into.
            System.out.print(System.getProperty("user.dir") + "- $  ");
            String strCommand = prompt.nextLine();
            String[] command = shell.splitCommand(strCommand);
            boolean historicalExec = false;

            //This determines what process to run based off of your input.
            do {
                historicalExec = false;
                switch (command[0]) {
                    case "exit":
                        shell.setActive(false);
                        break;
                    case "ptime":
                        pTime.printPTime();
                        break;
                    case "list":
                        List list = new List();
                        break;
                    case "cd":
                        ChangeDirectory cd = new ChangeDirectory(command);
                        break;
                    case "mdir":
                        MDir mDir = new MDir(command);
                        break;
                    case "rdir":
                        RDir rDir = new RDir(command);
                        break;
                    case "history":
                        history.printHistory();
                        break;
                    case "^":
                        String historicalCommand = history.execHistorical(command);
                        command = shell.splitCommand(historicalCommand);
                        historicalExec = true;
                        break;
                    default:
                        ExternalCommand externalCommand = new ExternalCommand(command, pTime);
                }
            } while (historicalExec);
            history.addCommand(strCommand);
        }
    }



    /**
     * This method provides a regex to split a command while maintaining certain conventions
     * such as double quotes. Taken from Dr. Mahdi Nasrullah Al-Ameen's code provided in the
     * source description
     * @param command The command string to split.
     * @return An array of Strings containing the command and arguments.
     */
    public String[] splitCommand(String command) {
        java.util.List<String> matchList = new java.util.ArrayList<>();

        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
        Matcher regexMatcher = regex.matcher(command);
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                // Add double-quoted string without the quotes
                matchList.add(regexMatcher.group(1));
            } else if (regexMatcher.group(2) != null) {
                // Add single-quoted string without the quotes
                matchList.add(regexMatcher.group(2));
            } else {
                // Add unquoted word
                matchList.add(regexMatcher.group());
            }
        }

        return matchList.toArray(new String[matchList.size()]);
    }




    //Getters
    public boolean getActive() {
        return active;
    }


    //Setters
    public void setActive(boolean setTo) {
        active = setTo;
    }
}

import java.util.ArrayList;
import java.util.Iterator;

public class History {
    private ArrayList history;

    public History() {
        this.history = new ArrayList();
    }

    public void addCommand(String command) {
        history.add(command);
    }

    public void printHistory() {
        Iterator iterator = history.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            System.out.println(index + " : " + iterator.next());
            index++;
        }
    }

    public String execHistorical(String[] command) {
        try {
            if (command.length > 1) {
                return history.get(Integer.parseInt(command[1]) - 1).toString();
            } else {
                System.out.println("History number is required.");
            }
        } catch (Exception e) {
            System.out.println("Invalid history number.");
        }
        return "";
    }
}

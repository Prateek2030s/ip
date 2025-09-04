package alex;

import java.io.FileNotFoundException;

/**
 * Represents the chatbot model used
 */
public class Alex {
    // Field for a horizontal line
    public static final String LINE = Alex.generateLine();
    private static final String filePath = "./data/alex.txt";

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Alex() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            taskList = new TaskList();
        }
    }

    /**
     * Generates a straight horizontal line
     * @return Straight horizontal line
     */
    public static String generateLine() {
        String s = "";
        for (int i = 0; i < 100; i++) {
            s = s + "_";
        }
        return s + "\n";
    }

    public void run() {
        String greeting = "Hello! I'm Alex.\n";
        String action = "What can I do for you?\n";
        System.out.println(LINE + greeting + action + LINE);
        ui.run(taskList, storage);
    }

    public String getResponse(String input) {
        return null;
    }

    public static void main(String[] args) {
        new Alex().run();

    }
}

package alex;

import java.io.FileNotFoundException;

/**
 * Represents the chatbot model <code>Alex</code>.
 * An <code>Alex</code> object manages the chatbot's task list, aliases, storage, and UI.
 * It loads saved data on startup and processes user input to produce chatbot responses.
 */
public class Alex {

    private static final String tasklistFilePath = "./data/alex.txt";
    private static final String aliasesListFilePath = "./data/alias.txt";

    private Storage storage;
    private TaskList taskList;
    private Alias aliases;
    private Ui ui;

    private String response = "";

    /**
     * Constructs an <code>Alex</code> chatbot.
     * Attempts to load saved task and alias data from storage. If the files are not found,
     * initializes with empty task list and alias set.
     */
    public Alex() {
        this.ui = new Ui();
        this.storage = new Storage(tasklistFilePath, aliasesListFilePath);
        try {
            this.taskList = new TaskList(storage.loadTasklist());
            this.aliases = storage.loadAliases();
        } catch (FileNotFoundException e) {
            response = response + "Saved files are not found";
            this.taskList = new TaskList();
            this.aliases = new Alias();
        }
    }

    /**
     * Returns the chatbot's response to the specified user input.
     *
     * @param input User's input to the chatbot.
     * @return Chatbot's response to the input provided by the user.
     */
    public String getResponse(String input) {
        if (!response.isEmpty()) {
            String message = response;
            response = "";
            return message;
        }
        return ui.run(taskList, storage, input, aliases);
    }
}


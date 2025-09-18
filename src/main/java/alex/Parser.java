package alex;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import alex.command.DeadlineTaskCommand;
import alex.command.DeleteTaskCommand;
import alex.command.EventTaskCommand;
import alex.command.TodoTaskCommand;

/**
 * Represents the procedure to understand user inputs.
 */
public class Parser {

    private String input;
    private TaskList taskList;
    private Storage storage;

    public Parser(String input, TaskList taskList, Storage storage) {
        this.input = input;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Separates the first word of the input from the rest.
     *
     * @return An array with the first element as the first word and second element
     *         as the remaining words in the input.
     */
    public String[] inputBreakdown() {
        return input.split(" ", 2);
    }

    /**
     *
     * @return The first word of the input which is the command.
     */
    public String firstWord() {
        assert this.inputBreakdown().length > 0 : "Input cannot be empty";
        return this.inputBreakdown()[0];
    }

    /**
     * Parses user inputs and returns the chatbot's response.
     *
     * @return Chatbot's response to parsed user input.
     * @throws AlexException If an invalid input is detected.
     */
    public String parseInput() throws AlexException {
        CommandType command = CommandType.stringToEnum(this.firstWord());

        switch (command) {
        case TODO:
            return new TodoTaskCommand(inputBreakdown()).execute(taskList, storage);
        case DEADLINE:
            return new DeadlineTaskCommand(inputBreakdown()).execute(taskList, storage);
        case EVENT:
            return new EventTaskCommand(inputBreakdown()).execute(taskList, storage);
        case LIST:
            return taskList.generateTaskList();
        case BYE:
            return this.parseByeInput();
        case HELLO:
            return this.parseGreetingInput();
        case MARK:
            return this.parseMarkInput();
        case UNMARK:
            return this.parseUnmarkInput();
        case FIND:
            return this.parseFindInput();
        case DELETE:
            return new DeleteTaskCommand(inputBreakdown()).execute(taskList, storage);
        default:
            return this.parseUnknownInput();
        }
    }

    /**
     * Creates an event task object based on the details provided and stores and
     * provides a response to the user for the input beginning with "event".
     *
     * @return The response of the chatbot after adding the event into the tasklist.
     * @throws AlexException If details of the event is missing.
     */


    /**
     * Creates a deadline task object based on the details provided and stores and
     * provides a response to the user for the input beginning with "deadline".
     *
     * @return The response of the chatbot after adding the deadline into the tasklist.
     * @throws AlexException If details of the deadline task is missing.
     */


    /**
     * Creates a todo task object based on the details provided and stores and
     * provides a response to the user for the input beginning with "todo".
     *
     * @return The response of the chatbot after adding the todo task into the tasklist.
     * @throws AlexException If details of the todo task is missing.
     */


    /**
     * Responds to the user for the input beginning with "hello".
     *
     * @return The response of the chatbot.
     */
    private String parseGreetingInput() {
        return "Hello, I'm Alex. What do you want from me";
    }

    /**
     * Responds to the user for the input beginning with "bye".
     *
     * @return The response of the chatbot.
     */
    private String parseByeInput() {
        return "Need to leave is it?\n" +  "Goodbye then, see you again soon!";
    }

    /**
     * Gives a list of tasks matching the string passed to find.
     *
     * @return List of matching tasks.
     */
    private String parseFindInput() {
        return taskList.findMatch(this.inputBreakdown()[1]);
    }

    /**
     * Deletes the stated task in the list.
     *
     * @return A response of the chatbot after deleting the stated task.
     * @throws AlexException If task to be deleted is not stated or the task to be deleted is not
     *                       in the list.
     */

    /**
     * Marks the stated task in the list.
     *
     * @return A response of the chatbot after marking the stated task.
     * @throws AlexException If task to be deleted is not stated or the task to be deleted is not
     *                       in the list.
     */
    private String parseMarkInput() throws AlexException {
        // Checks if the task to be marked is not specified to allow the feature to work
        if (inputBreakdown().length <= 1) {
            throw new AlexException("Please state which task you would like to mark");
        }

        int taskNumber = Integer.parseInt(inputBreakdown()[1]);

        assert taskList.size() >=0 : "The length of the list should be non-negative";
        // Checks if the task number stated is invalid to allow the feature to work
        if (taskNumber > taskList.size() || taskNumber < 0) {
            throw new AlexException("Invalid number, please try again");
        }

        taskList.mark(taskNumber);

        // Stores the current tasklist into hard disk to keep track
        try {
            storage.saveTask(taskList);
        }
        catch (IOException e) {
            return ("File not found. Unable to save");
        } finally {
            return "Amazing, Task " + taskNumber + " marked.";
        }
    }

    /**
     * Unmarks the stated task in the list.
     *
     * @return A response of the chatbot after unmarking the stated task.
     * @throws AlexException If task to be deleted is not stated or the task to be deleted is not
     *                       in the list.
     */
    private String parseUnmarkInput() throws AlexException {
        // Checks if the task to be unmarked is not specified to allow the feature to work
        if (inputBreakdown().length <= 1) {
            throw new AlexException("Please state which task you like to unmark");
        }

        int taskNumber = Integer.parseInt(inputBreakdown()[1]);

        assert taskList.size() >=0 : "The length of the list should be non-negative";
        // Checks if the task number stated is invalid to allow the feature to work
        if (taskNumber > taskList.size() || taskNumber < 0) {
            throw new AlexException("Invalid number, please try again");
        }
        taskList.unmark(taskNumber);

        // Stores the current tasklist into hard disk to keep track
        try {
            storage.saveTask(taskList);
        }
        catch (IOException e) {
            return ("File not found. Unable to save");
        } finally {
            return  "Amazing, Task " + taskNumber + " unmarked.";
        }
    }

    /**
     * Handles the input given that is not recognised.
     *
     * @return Nothing in this method
     * @throws AlexException Response to unknown inputs.
     */
    private String parseUnknownInput() throws AlexException {
        throw new AlexException("HAHAHA but I don't know what it means!");
    }

}

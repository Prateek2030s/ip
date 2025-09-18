package alex;

import alex.command.ByeCommand;
import alex.command.DeadlineTaskCommand;
import alex.command.DeleteTaskCommand;
import alex.command.EventTaskCommand;
import alex.command.FindTaskCommand;
import alex.command.HelloCommand;
import alex.command.ListCommand;
import alex.command.MarkTaskCommand;
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
     * @throws AlexException If any invalid input is detected.
     */
    public String parseInput() throws AlexException {
        CommandType command = CommandType.stringToEnum(this.firstWord());

        switch (command) {
        case TODO:
            return new TodoTaskCommand(inputBreakdown(), taskList, storage).response();
        case DEADLINE:
            return new DeadlineTaskCommand(inputBreakdown(), taskList, storage).response();
        case EVENT:
            return new EventTaskCommand(inputBreakdown(), taskList, storage).response();
        case LIST:
            return new ListCommand(taskList).response();
        case BYE:
            return new ByeCommand().response();
        case HELLO:
            return new HelloCommand().response();
        case MARK:
            return new MarkTaskCommand(inputBreakdown(), taskList, storage, true).response();
        case UNMARK:
            return new MarkTaskCommand(inputBreakdown(), taskList, storage, false).response();
        case FIND:
            return new FindTaskCommand(inputBreakdown(), taskList).response();
        case DELETE:
            return new DeleteTaskCommand(inputBreakdown(), taskList, storage).response();
        default:
            throw new AlexException("HAHAHA I don't know what it means!");
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


    /**
     * Responds to the user for the input beginning with "bye".
     *
     * @return The response of the chatbot.
     */


    /**
     * Gives a list of tasks matching the string passed to find.
     *
     * @return List of matching tasks.
     */


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


    /**
     * Unmarks the stated task in the list.
     *
     * @return A response of the chatbot after unmarking the stated task.
     * @throws AlexException If task to be deleted is not stated or the task to be deleted is not
     *                       in the list.
     */


    /**
     * Handles the input given that is not recognised.
     *
     * @return Nothing in this method
     * @throws AlexException Response to unknown inputs.
     */


}

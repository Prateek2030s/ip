package alex;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public String[] inputBreakdown() {
        return input.split(" ", 2);
    }

    public String firstWord() {
        return this.inputBreakdown()[0];
    }

    /**
     * Parses user inputs and returns the chatbot's response.
     *
     * @return Chatbot's response to parsed user input.
     * @throws AlexException if invalid inputs are passed into
     */
    public String parseInput() throws AlexException {

        if (firstWord().equals("bye")) {
            return this.parseByeInput();

        } else if (firstWord().equals("list")) {
            return taskList.generateTaskList();

        } else if (firstWord().equals("mark")) {

            if (inputBreakdown().length <= 1) {
                throw new AlexException("Please state which task you would like to mark");
            }

            int taskNumber = Integer.parseInt(inputBreakdown()[1]);

            if (taskNumber > taskList.size() || taskNumber < 0) {
                throw new AlexException("Invalid number, please try again");
            }

             taskList.mark(taskNumber);


            try {
                storage.saveTask(taskList);
            }
            catch (IOException e) {
                return ("File not found. Unable to save");
            } finally {
                return "Amazing, Task " + taskNumber + " marked.";
            }

        } else if (firstWord().equals("unmark")) {

            if (inputBreakdown().length <= 1) {
                throw new AlexException("Please state which task you like to unmark");
            }

            int taskNumber = Integer.parseInt(inputBreakdown()[1]);

            if (taskNumber > taskList.size() || taskNumber < 0) {
                throw new AlexException("Invalid number, please try again");
            }
              taskList.unmark(taskNumber);

            try {
                storage.saveTask(taskList);
            }
            catch (IOException e) {
                return ("File not found. Unable to save");
            } finally {
                return  "Amazing, Task " + taskNumber + " unmarked.";
            }

        } else if (firstWord().equals("todo")) {

            if (inputBreakdown().length <= 1) {
                throw new AlexException("Please state what you would like todo");
            }

            Task toAdd = new Todo(inputBreakdown()[1]);
            taskList.add(toAdd);

            try {
                storage.saveTask(taskList);
            }
            catch (IOException e) {
                return ("File not found. Unable to save");
            } finally {
                String addTask = String.format("Ok, I've added this task: %s\n", toAdd);
                String taskLength = "Watch out, you have " + taskList.size() + " tasks left.";
                return addTask + taskLength;
            }

        } else if (firstWord().equals("deadline")) {

            if (inputBreakdown().length <= 1) {
                throw new AlexException("Please state what deadline you have and by when");
            }

            String[] taskBreakdown = inputBreakdown()[1].split(" /by ");
            String date = LocalDate.parse(taskBreakdown[1]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            Task toAdd = new Deadline(taskBreakdown[0], date);
            taskList.add(toAdd);

            try {
                storage.saveTask(taskList);
            }
            catch (IOException e) {
                return ("File not found. Unable to save");
            } finally {
                String addTask = String.format("Ok, I've added this task: %s\n", toAdd);
                String taskLength = "Watch out, you have " + taskList.size() + " tasks left.";
                return addTask + taskLength;
            }

        } else if (firstWord().equals("event")) {

            if (inputBreakdown().length <= 1) {
                throw new AlexException("Please state when do you have the event");
            }

            String[] taskBreakdown = inputBreakdown()[1].split(" / ");
            Task toAdd = new Event(taskBreakdown[0], taskBreakdown[1],taskBreakdown[2]);
            taskList.add(toAdd);

            try {
                storage.saveTask(taskList);
            }
            catch (IOException e) {
                return ("File not found. Unable to save");
            } finally {
                String addTask = String.format("Ok, I've added this task: %s\n", toAdd);
                String taskLength = "Watch out, you have " + taskList.size() + " tasks left.";
                return addTask + taskLength;
            }

        } else if (firstWord().equals("delete")) {
            return this.parseDeleteInput();
        } else if (firstWord().equals("find")) {
            return this.parseFindInput();
        } else if (firstWord().equals("hello")) {
            return parseGreetingInput();
        } else {
            throw new AlexException("HAHAHA but I don't know what it means!");
        }
    }

    public String parseGreetingInput() {
        return "Hello, I'm Alex. What do you want from me";
    }

    public String parseByeInput() {
        return "Need to leave is it?\n" +  "Goodbye then, see you again soon!";
    }

    public String parseFindInput() {
        return taskList.findMatch(this.inputBreakdown()[1]);
    }

    public String parseDeleteInput() throws AlexException {
        if (inputBreakdown().length <= 1) {
            throw new AlexException("Please state which task to delete");
        }

        int next = Integer.parseInt(inputBreakdown()[1]);

        if (next > taskList.size() || next < 0) {
            throw new AlexException("Invalid number, please try again");
        }

        Task removed = taskList.remove(next - 1);

        try {
            storage.saveTask(taskList);
        }
        catch (IOException e) {
            return ("File not found. Unable to save");
        } finally {
            String deleteTask = String.format("Ok, I've deleted this task: %s\n", removed);
            String taskLength = "Watch out, you have " + taskList.size() + " tasks left.";
            return deleteTask + taskLength;
        }
    }




}

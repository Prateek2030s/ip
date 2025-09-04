package alex;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the procedure to understand user inputs
 */
public class Parser {
    private String input;

    public Parser(String input) {
        this.input = input;
    }

    /**
     * Parses user inputs
     *
     * @param taskList List of task used currently
     * @param storage Current storage of tasklist
     * @throws AlexExecption if invalid inputs are passed into
     */
    public String parseInput(TaskList taskList, Storage storage) throws AlexExecption {
        String[] splitter = input.split(" ", 2);
        String firstPart = splitter[0];

        if (firstPart.equals("bye")) {
            return "Need to leave is it?\n" +  "Goodbye then, see you again soon!";

        } else if (firstPart.equals("list")) {
            return taskList.generateTaskList();

        } else if (firstPart.equals("mark")) {

            if (splitter.length <= 1) {
                throw new AlexExecption("Please state which task you would like to mark");
            }

            int taskNumber = Integer.parseInt(splitter[1]);

            if (taskNumber > taskList.size() || taskNumber < 0) {
                throw new AlexExecption("Invalid number, please try again");
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

        } else if (firstPart.equals("unmark")) {

            if (splitter.length <= 1) {
                throw new AlexExecption("Please state which task you like to unmark");
            }

            int taskNumber = Integer.parseInt(splitter[1]);

            if (taskNumber > taskList.size() || taskNumber < 0) {
                throw new AlexExecption("Invalid number, please try again");
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

        } else if (firstPart.equals("todo")) {

            if (splitter.length <= 1) {
                throw new AlexExecption("Please state what you would like todo");
            }

            Task toAdd = new Todo(splitter[1]);
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

        } else if (firstPart.equals("deadline")) {

            if (splitter.length <= 1) {
                throw new AlexExecption("Please state what deadline you have and by when");
            }

            String[] taskBreakdown = splitter[1].split(" /by ");
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

        } else if (firstPart.equals("event")) {

            if (splitter.length <= 1) {
                throw new AlexExecption("Please state when do you have the event");
            }

            String[] taskBreakdown = splitter[1].split(" / ");
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

        } else if (firstPart.equals("delete")) {

            if (splitter.length <= 1) {
                throw new AlexExecption("Please state which task to delete");
            }

            int next = Integer.parseInt(splitter[1]);

            if (next > taskList.size() || next < 0) {
                throw new AlexExecption("Invalid number, please try again");
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

        } else if (firstPart.equals("find")) {
            return "Here is what I've found:\n" + taskList.findMatch(splitter[1]);
        } else if (firstPart.equals("hello")) {
            return "I'm Alex. What do you want from me";
        } else {
            throw new AlexExecption("HAHAHA but I don't know what it means!");
        }
    }


}

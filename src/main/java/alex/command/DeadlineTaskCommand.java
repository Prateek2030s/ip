package alex.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import alex.AlexException;
import alex.Deadline;
import alex.Storage;
import alex.Task;
import alex.TaskList;

public class DeadlineTaskCommand extends TaskCommand {

    public DeadlineTaskCommand(String[] inputBreakdown) {
        super(inputBreakdown);
    }

    public String[] taskBreakdown() throws AlexException {
        return this.getTarget("Please state what deadline you have and by when").split(" /by ");
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws AlexException {
        // Creates the Event object so that it can be added into the tasklist
        String DeadlineDescription = taskBreakdown()[0];
        String DeadlineDate = LocalDate.parse(taskBreakdown()[1]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        Task toAdd = new Deadline(DeadlineDescription, DeadlineDate);
        taskList.add(toAdd);

        // Stores the current tasklist into hard disk to keep track
        try {
            storage.saveTask(taskList);
        }
        catch (IOException e) {
            return ("File not found. Unable to save");
        } finally {
            String addTask = String.format("Ok, I've added this task: %s\n", toAdd);
            String taskLength = "Watch out, you have " + taskList.size() + " tasks left.";
            return response(addTask + taskLength);
        }
    }

    @Override
    public String response(String message) {
        return message;
    }
}

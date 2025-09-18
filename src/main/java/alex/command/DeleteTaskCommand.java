package alex.command;

import java.io.IOException;

import alex.AlexException;
import alex.Storage;
import alex.Task;
import alex.TaskList;

public class DeleteTaskCommand extends TaskCommand {

    public DeleteTaskCommand(String[] inputBreakdown) {
        super(inputBreakdown);
    }

    public int taskNumber() throws AlexException {
        return Integer.parseInt(this.getTarget("Please state which task to delete"));
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws AlexException {

        assert taskList.size() >=0 : "The length of the list should be non-negative";
        if (taskNumber() > taskList.size() || taskNumber() < 0) {
            throw new AlexException("Invalid number, please try again");
        }

        Task deleteTask = taskList.remove(taskNumber() - 1);

        // Stores the current tasklist into hard disk to keep track
        try {
            storage.saveTask(taskList);
        }
        catch (IOException e) {
            return ("File not found. Unable to save");
        } finally {
            String afterDeleteTask = String.format("Ok, I've deleted this task: %s\n", deleteTask);
            String taskLength = "Watch out, you have " + taskList.size() + " tasks left.";
            return response(afterDeleteTask + taskLength);
        }
    }

    @Override
    public String response(String message) {
        return message;
    }
}

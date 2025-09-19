package alex.command;

import java.io.IOException;

import alex.AlexException;
import alex.Storage;
import alex.Task;
import alex.TaskList;

public class DeleteTaskCommand extends TaskCommand {

    private Storage storage;

    public DeleteTaskCommand(String[] inputBreakdown, TaskList taskList, Storage storage) {
        super(inputBreakdown, taskList);
        this.storage = storage;
    }

    public int taskNumber() throws AlexException {
        return Integer.parseInt(this.getTarget("Please state which task to delete"));
    }

    @Override
    public String execute() throws AlexException {

        assert getTaskList().size() >=0 : "The length of the list should be non-negative";
        if (taskNumber() > getTaskList().size() || taskNumber() < 0) {
            throw new AlexException("Invalid number, please try again");
        }

        Task deleteTask = getTaskList().remove(taskNumber() - 1);

        // Stores the current tasklist into hard disk to keep track
        try {
            storage.saveTask(getTaskList());
        }
        catch (IOException e) {
            return ("File not found. Unable to save");
        } finally {
            String afterDeleteTask = String.format("Ok, I've deleted this task: %s\n", deleteTask);
            String taskLength = "Watch out, you have " + getTaskList().size() + " tasks left.";
            return afterDeleteTask + taskLength;
        }
    }

    @Override
    public String response() throws AlexException {
        return this.execute();
    }
}

package alex.command;

import java.io.IOException;

import alex.AlexException;
import alex.Storage;
import alex.Task;
import alex.TaskList;
import alex.Todo;

public class TodoTaskCommand extends TaskCommand {

    private Storage storage;

    public TodoTaskCommand(String[] inputBreakdown, TaskList taskList, Storage storage)  {
        super(inputBreakdown, taskList);
        this.storage = storage;
    }

    @Override
    public String execute() throws AlexException {
        // Creates the Todo object so that it can be added into the tasklist
        Task toAdd = new Todo(this.getTarget("Please state what you would like todo"));
        TaskList taskList = getTaskList();
        taskList.add(toAdd);

        // Stores the current tasklist into hard disk to keep track
        try {
            storage.saveTask(taskList);
        } catch (IOException e) {
            return ("File not found. Unable to save");
        } finally {
            String addTask = String.format("Ok, I've added this task: %s\n", toAdd);
            String taskLength = "Watch out, you have " + taskList.size() + " tasks left.";
            return addTask + taskLength;
        }
    }

    @Override
    public String response() throws AlexException {
        return this.execute();
    }

}

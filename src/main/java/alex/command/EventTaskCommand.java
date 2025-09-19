package alex.command;

import java.io.IOException;

import alex.AlexException;
import alex.Event;
import alex.Storage;
import alex.Task;
import alex.TaskList;

public class EventTaskCommand extends TaskCommand {

    private Storage storage;

    public EventTaskCommand(String[] inputBreakdown, TaskList taskList, Storage storage) {
        super(inputBreakdown, taskList);
        this.storage = storage;
    }

    public String[] taskBreakdown() throws AlexException {
        return this.getTarget("Please state when do you have the event").split(" / ");
    }

    @Override
    public String execute() throws AlexException {
        // Creates the Event object so that it can be added into the tasklist
        String eventDescription = taskBreakdown()[0];
        String eventFromTime = taskBreakdown()[1];
        String eventEndTime = taskBreakdown()[2];

        Task toAdd = new Event(eventDescription,eventFromTime,eventEndTime);
        getTaskList().add(toAdd);

        // Stores the current tasklist into hard disk to keep track
        try {
            storage.saveTask(getTaskList());
        }
        catch (IOException e) {
            return ("File not found. Unable to save");
        } finally {
            String addTask = String.format("Ok, I've added this task: %s\n", toAdd);
            String taskLength = "Watch out, you have " + getTaskList().size() + " tasks left.";
            return addTask + taskLength;
        }
    }

    @Override
    public String response() throws AlexException {
        return this.execute();
    }
}

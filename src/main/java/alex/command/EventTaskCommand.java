package alex.command;

import java.io.IOException;

import alex.AlexException;
import alex.Event;
import alex.Storage;
import alex.Task;
import alex.TaskList;

public class EventTaskCommand extends TaskCommand {

    public EventTaskCommand(String[] inputBreakdown) {
        super(inputBreakdown);
    }

    public String[] taskBreakdown() throws AlexException {
        return this.getTarget("Please state when do you have the event").split(" / ");
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws AlexException {
        // Creates the Event object so that it can be added into the tasklist
        String eventDescription = taskBreakdown()[0];
        String eventFromTime = taskBreakdown()[1];
        String eventEndTime = taskBreakdown()[2];

        Task toAdd = new Event(eventDescription,eventFromTime,eventEndTime);
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

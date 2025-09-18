package alex.command;

import java.io.IOException;

import alex.AlexException;
import alex.Storage;
import alex.TaskList;

public class MarkTaskCommand extends TaskCommand {

    private Storage storage;
    private boolean isMark;

    public MarkTaskCommand(String[] inputBreakdown, TaskList taskList, Storage storage, boolean isMark) {
        super(inputBreakdown, taskList);
        this.storage = storage;
        this.isMark = isMark;
    }

    public int taskNumber() throws AlexException {
        String errorMessage;
        if (isMark) {
            errorMessage = "Please state which task you would like to mark";
        } else {
            errorMessage = "Please state which task you would like to unmark";
        }
        return Integer.parseInt(this.getTarget(errorMessage));
    }

    public String markType(int taskNumber) {
        if (isMark) {
            getTaskList().mark(taskNumber);
            return "Amazing, Task " + taskNumber + " marked.";
        } else {
            getTaskList().unmark(taskNumber);
            return "Amazing, Task " + taskNumber + " unmarked.";
        }
    }

    @Override
    public String execute() throws AlexException {

        assert getTaskList().size() >=0 : "The length of the list should be non-negative";
        if (taskNumber() > getTaskList().size() || taskNumber() < 0) {
            throw new AlexException("Invalid number, please try again");
        }

        String responseMessage = markType(taskNumber());

        // Stores the current tasklist into hard disk to keep track
        try {
            storage.saveTask(getTaskList());
        }
        catch (IOException e) {
            return ("File not found. Unable to save");
        } finally {
            return responseMessage;
        }
    }

    @Override
    public String response() throws AlexException {
        return this.execute();
    }


}

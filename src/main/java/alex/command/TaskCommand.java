package alex.command;

import alex.AlexException;
import alex.Storage;
import alex.TaskList;

public abstract class TaskCommand implements Command {

    private String[] inputBreakdown;
    private TaskList taskList;

    public TaskCommand(String[] inputBreakdown, TaskList taskList)  {
      this.inputBreakdown = inputBreakdown;
      this.taskList = taskList;
    }

    public abstract String execute() throws AlexException;

    public String[] getInputBreakdown() {
        return this.inputBreakdown;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public String getTarget(String errorMessage) throws AlexException {
        if (this.getInputBreakdown().length <= 1) {
            throw new AlexException(errorMessage);
        }
        return this.getInputBreakdown()[1];
    }
}

package alex.command;

import alex.AlexException;
import alex.Storage;
import alex.TaskList;

public class FindTaskCommand extends TaskCommand {

    public FindTaskCommand(String[] inputBreakdown, TaskList taskList) {
        super(inputBreakdown, taskList);
    }

    @Override
    public String execute() throws AlexException {
        String taskToFind = this.getTarget("Please state the item to find");
        String taskMatch = getTaskList().findMatch(taskToFind);
        String afterFind = "Here are the matching tasks in your list:\n";
        return afterFind + taskMatch;
    }

    @Override
    public String response() throws AlexException {
        return this.execute();
    }
}

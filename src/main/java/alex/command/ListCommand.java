package alex.command;

import alex.TaskList;

public class ListCommand implements Command {

    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String response() {
        return taskList.generateTaskList();
    }

}

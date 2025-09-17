package alex;

public abstract class TaskCommand implements Command {

    private String[] inputBreakdown;

    public TaskCommand(String[] inputBreakdown) throws AlexException {
      this.inputBreakdown = inputBreakdown;
    }

    public String[] getInputBreakdown() {
        return this.inputBreakdown;
    }

    public abstract String execute(TaskList taskList, Storage storage);
}

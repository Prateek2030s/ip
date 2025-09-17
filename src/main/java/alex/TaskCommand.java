package alex;

public abstract class TaskCommand implements Command {

    private String[] inputBreakdown;

    public TaskCommand(String[] inputBreakdown)  {
      this.inputBreakdown = inputBreakdown;
    }

    public abstract String execute(TaskList taskList, Storage storage) throws AlexException;

    public String[] getInputBreakdown() {
        return this.inputBreakdown;
    }

    public String getTarget(String errorMessage) throws AlexException {
        if (this.getInputBreakdown().length <= 1) {
            throw new AlexException(errorMessage);
        }
        return this.getInputBreakdown()[1];
    }
}

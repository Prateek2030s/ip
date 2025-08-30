package alex;

public abstract class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public abstract String toFileString();

    public String getDescription() {
        return this.description;
    }
    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    // To indicate whether it is 0 or 1 in fileToString
    public int doTaskState() {
        if (isDone) {
            return 0;
        } else {
            return 1;
        }
    }

    // To indicate to the task object based on file reading
    public void handleTask(int i) {
        if (i == 0) {
            this.markTask();
        } else {
            this.unmarkTask();
        }
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}

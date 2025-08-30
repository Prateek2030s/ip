package alex;

/**
 * Represents a general task to be included in the list
 */
public abstract class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Format in which tasks are saved in storage
     * @return
     */
    public abstract String toFileString();

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks task as done
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Unmarks tasks
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Number version to indicate as done or not done
     * @return 0 if done and 1 if not done
     */
    public int taskState() {
        if (isDone) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Converts number stored to actual representation of done or not done
     * @param i 0 if done and 1 if not done
     */
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

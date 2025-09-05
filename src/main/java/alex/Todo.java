package alex;

/**
 * Type of task to indicate what to do.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        return "T / " + this.doTaskState() + " / " + this.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

package alex;

/**
 * Type of task to indicate deadline
 */
public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toFileString() {
        return "D / " + this.doTaskState() + " / " + this.getDescription() + " / " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}

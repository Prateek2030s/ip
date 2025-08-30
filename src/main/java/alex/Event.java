package alex;

/**
 * Type of task to indicate an event
 */
public class Event extends Task {

    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        return "E / " + this.taskState() + " / " + this.getDescription() + " / " + this.from + " " + this.to;
    }

    @Override
    public String toString() {
        String[] fromSplit = from.split("from");
        String[] toSplit = to.split("to");
        return "[E]" + super.toString() + " (from:" + fromSplit[1] + " to:" + toSplit[1] + ")";
    }
}

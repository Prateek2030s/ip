package alex;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        return "T / " + this.taskState() + " / " + this.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

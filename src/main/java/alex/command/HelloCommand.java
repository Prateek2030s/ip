package alex.command;

public class HelloCommand implements Command {

    @Override
    public String response() {
        return "Hello, I'm Alex. What do you want from me";
    }
}

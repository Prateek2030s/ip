package alex.command;

public class ByeCommand implements Command {

    @Override
    public String response() {
        return "Need to leave is it?\n" +  "Goodbye then, see you again soon!";
    }

}

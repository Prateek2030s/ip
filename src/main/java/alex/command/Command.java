package alex.command;

import alex.AlexException;

public interface Command {

    String response() throws AlexException;

}

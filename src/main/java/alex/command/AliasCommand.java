package alex.command;

import java.io.IOException;

import alex.AlexException;
import alex.Alias;
import alex.CommandType;
import alex.Storage;
import alex.TaskList;

public class AliasCommand extends TaskCommand {

    private Alias aliases;
    private Storage storage;

    public AliasCommand(String[] inputBreakdown, Alias aliases,Storage storage) {
        super(inputBreakdown, null);
        this.aliases = aliases;
        this.storage = storage;
    }

    public String[] taskBreakdown() throws AlexException {
        return this.getTarget("Please state which alias you would like to create").split(" ");
    }

    @Override
    public String execute() throws AlexException {
        String originalWord = taskBreakdown()[0];
        CommandType command = CommandType.stringToEnum(originalWord);
        String responseMessage = "";

        if (originalWord.equals("roll")) {
            return "Here is your alias roll:\n" + aliases.listOfAliases();
        } else {
            String aliasWord = taskBreakdown()[1];
            aliases.setAlias(command, aliasWord);
            responseMessage = responseMessage + "Nice! I have added this alias:\n"
                    + command.getKeyword()
                    + ": " + aliasWord;
        }

        try {
            storage.saveAliases(aliases);
        } catch (IOException e) {
            return ("File not found. Unable to save");
        } finally {
            return responseMessage;
        }
    }

    @Override
    public String response() throws AlexException {
        return this.execute();
    }

}

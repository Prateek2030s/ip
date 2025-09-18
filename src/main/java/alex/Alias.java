package alex;

import java.util.HashMap;

public class Alias {

    private HashMap<CommandType, String> aliasMap;

    public Alias(HashMap<CommandType, String> aliasMap) {
        this.aliasMap = aliasMap;
    }

    public void setAlias(CommandType command, String alias) {
        aliasMap.put(command, alias);
    }

    public String listOfAliases() {
        StringBuilder sb = new StringBuilder();
        for (CommandType command : aliasMap.keySet()) {
            String alias = aliasMap.get(command);
            if (alias != null) {
                sb.append(command.getKeyword())
                        .append(": ")
                        .append(alias)
                        .append("\n");
            }
        }
        return sb.toString().trim();
    }

}

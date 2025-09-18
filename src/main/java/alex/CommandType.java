package alex;

public enum CommandType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    FIND("find"),
    HELLO("hello"),
    BYE("bye"),
    UNKNOWN("unknown");

    private String keyword;

    CommandType(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public static CommandType stringToEnum(String input) {
        for (CommandType c : CommandType.values()) {
            if (c.keyword.equalsIgnoreCase(input)) {
                return c;
            }
        }
        return UNKNOWN;
    }
}

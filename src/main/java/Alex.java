public class Alex {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Alex.\n";
        String action = "What can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!\n";
        String line = "";
        for (int i = 0; i < 100; i++) {
            line = line + "_";
        }
        line = line + "\n";
        System.out.println(line + greeting + action + line + goodbye + line);

    }
}

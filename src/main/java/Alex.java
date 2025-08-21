import java.util.Scanner;

public class Alex {

    // Method to echo
    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        if (s.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!\n");
            scanner.close();
        } else {
            System.out.println(s + "\n");
            echo();
        }
    }
    public static void main(String[] args) {
        String greeting = "Hello! I'm Alex.\n";
        String action = "What can I do for you?\n";
        String line = "";
        for (int i = 0; i < 100; i++) {
            line = line + "_";
        }
        line = line + "\n";
        System.out.println(line + greeting + action + line);
        echo();

    }
}

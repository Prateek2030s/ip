import java.util.Scanner;

public class Alex {

    // Method to generate a horizontal line
    private static String lineGenerator() {
        String s = "";
        for (int i = 0; i < 100; i++) {
            s = s + "_";
        }
        return s + "\n";
    }

    // field for a horizontal line
    private static String line = Alex.lineGenerator();

    // Method to echo
    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        if (s.equals("bye")) {
            System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
            scanner.close();
        } else {
            System.out.println(line + s + "\n" + line);
            echo();
        }
    }
    public static void main(String[] args) {
        String greeting = "Hello! I'm Alex.\n";
        String action = "What can I do for you?\n";
        System.out.println(line + greeting + action + line);
        echo();

    }
}

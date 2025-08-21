import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alex {
    // Field for a horizontal line
    private static String line = Alex.lineGenerator();

    // Field for a list of tasks
    private static List<String> itemList  = new ArrayList<>();
    // Method to generate a horizontal line
    private static String lineGenerator() {
        String s = "";
        for (int i = 0; i < 100; i++) {
            s = s + "_";
        }
        return s + "\n";
    }




    // Method to echo with adding task feature
    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (s.equals("bye")) {
            System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
            scanner.close();
        } else if (s.equals("list")) {
            int j = 1;
            String ans = "";
            for (int i = 0; i < itemList.size(); i++) {
                ans = ans +  j + ". " + itemList.get(i) + "\n";
                j++;
            }
            System.out.println(line + ans + line);
            echo();
        } else {
            itemList.add(s);
            System.out.println(line + "added: " + s + ". Amazing!\n" + line);
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

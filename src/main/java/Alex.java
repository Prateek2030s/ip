import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alex {
    // Field for a horizontal line
    private static String line = Alex.lineGenerator();

    // Field for a list of tasks
    private static List<Task> taskList = new ArrayList<>();

    // Method to generate a horizontal line
    private static String lineGenerator() {
        String s = "";
        for (int i = 0; i < 100; i++) {
            s = s + "_";
        }
        return s + "\n";
    }


    // Method which has features
    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] splitter = s.split(" ");
        String firstPart = splitter[0];
        if (firstPart.equals("bye")) {
            System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
            scanner.close();
        } else if (firstPart.equals("list")) {
            int j = 1;
            String ans = "";
            for (Task task : taskList) {
                ans = ans + j + ". " + task + "\n";
                j++;
            }
            System.out.println(line + ans + line);
            echo();
        } else if (firstPart.equals("mark")) {
            int next = Integer.parseInt(splitter[1]);
            taskList.get(next - 1).markTask();
            System.out.println(line + "Nice! I've marked this task as done:\n" + taskList.get(next - 1) + "\n" + line);
            echo();
        } else if (firstPart.equals("unmark")) {
            int next = Integer.parseInt(splitter[1]);
            Task task = taskList.get(next - 1);
            task.unmarkTask();
            System.out.println(line + "Ok, I've marked this task as not done yet:\n" + task + "\n" + line);
            echo();
        } else {
            taskList.add(new Task(s));
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

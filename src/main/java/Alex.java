import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alex {
    // Field for a horizontal line
    private static String line = Alex.lineGenerator();

    // Field for a list of tasks
    private static List<String> itemList  = new ArrayList<>();

    private static List<Task> taskList = new ArrayList<>();
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
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                ans = ans + j + ". " + task + "\n";
                //ans = ans +  j + ". " + itemList.get(i) + "\n";
                j++;
            }
            System.out.println(line + ans + line);
            echo();
        } else if (s.equals("mark")) {
            int next = scanner.nextInt();
            taskList.get(next - 1).markTask();
            System.out.println(line + "Nice! I've marked this task as done:\n" + taskList.get(next - 1) + "\n" + line);
            echo();
        } else if (s.equals("unmark")) {
            int next = scanner.nextInt();
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

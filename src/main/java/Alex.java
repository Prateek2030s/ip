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
        String[] splitter = s.split(" ", 2);
        String firstPart = splitter[0];
        String afterAdd1 = line + "Got it. I've added this task:\n";
        String afterAdd2 = String.format("Now you have %d tasks in the list.\n", taskList.size() + 1) + line;
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
        } else if (firstPart.equals("todo")) {
            Task toAdd = new Todo(splitter[1]);
            taskList.add(toAdd);
            System.out.println(afterAdd1 + toAdd + "\n" + afterAdd2);
            echo();
        } else if (firstPart.equals("deadline")) {
            String[] taskBreakdown = splitter[1].split(" /by ");
            Task toAdd = new Deadline(taskBreakdown[0], taskBreakdown[1]);
            taskList.add(toAdd);
            System.out.println(afterAdd1 + toAdd + "\n" + afterAdd2);
            echo();
        } else if (firstPart.equals("event")) {
            String[] taskBreakdown = splitter[1].split(" /");
            Task toAdd = new Event(taskBreakdown[0], taskBreakdown[1],taskBreakdown[2]);
            taskList.add(toAdd);
            System.out.println(afterAdd1 + toAdd + "\n" + afterAdd2);
            echo();
        }
    }
    public static void main(String[] args) {
        String greeting = "Hello! I'm Alex.\n";
        String action = "What can I do for you?\n";
        System.out.println(line + greeting + action + line);
      //  String s = "deadline return book /by sunday";
       // String[] p = s.split(" /by ");
       // String s = "event project meeting /from Mon 2pm /to 4pm";
      //  String[] p = s.split(" /");
       // System.out.println(p.length);
       // System.out.println(p[2]);
        echo();

    }
}

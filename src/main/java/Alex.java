import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alex {
    // Field for a horizontal line
    private static final String line = Alex.lineGenerator();

    // Field for a list of tasks
    private static List<Task> taskList;

    // Method to generate a horizontal line
    private static String lineGenerator() {
        String s = "";
        for (int i = 0; i < 100; i++) {
            s = s + "_";
        }
        return s + "\n";
    }

    // Generate task list with numbers
    private static String fullTaskList() {
        int i = 1;
        String ans = "Here are the tasks in your list:\n";
        for (Task t : taskList) {
            ans = ans + i + "." + t + "\n";
            i++;
        }

        return ans;
    }

    // Method to save task list
    private static void saveTask() throws IOException {
        FileWriter fw = new FileWriter("./data/alex.txt");
        StringBuilder s = new StringBuilder();
        for (Task t : taskList) {
            s.append(t.toFileString()).append(System.lineSeparator());
        }
        fw.write(String.valueOf(s));
        fw.close();
    }

    // Method to loadTask
    private static void loadTask() throws FileNotFoundException {
        File f = new File("./data/alex.txt");
        Scanner sc = new Scanner(f);
        taskList = new ArrayList<>();
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] parts = s.split(" / ");
            String type = parts[0];
            int taskState = Integer.parseInt(parts[1]);
            String description = parts[2];
            switch (type) {
                case "T":
                    Task t = new Todo(description);
                    t.handleTask(taskState);
                    taskList.add(t);
                    break;
                case "D":
                    Task t1 = new Deadline(description, parts[3]);
                    t1.handleTask(taskState);
                    taskList.add(t1);
                    break;
                case "E":
                    Task t2 = new Event(description, parts[3], parts[4]);
                    t2.handleTask(taskState);
                    taskList.add(t2);
                    break;
            }
        }
        sc.close();
    }


    // Method which has features
    public static void echo() throws AlexExecption{
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] splitter = s.split(" ", 2);
        String firstPart = splitter[0];
        String afterAdd1 = line + "Got it. I've added this task:\n";
        String afterAdd2 = String.format("Now you have %d tasks in the list.\n", taskList.size() + 1) + line;
        String afterDelete = String.format("Now you have %d tasks in the list.\n", taskList.size() - 1) + line;

        if (firstPart.equals("bye")) {
            System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
            scanner.close();

        } else if (firstPart.equals("list")) {
            System.out.println(line + Alex.fullTaskList() + line);
            echo();

        } else if (firstPart.equals("mark")) {

            if (splitter.length <= 1) {
                throw new AlexExecption("Please state which task you would like to mark");
            }

            int next = Integer.parseInt(splitter[1]);

            if (next > taskList.size() || next < 0) {
                throw new AlexExecption("Invalid number, please try again");
            }

            taskList.get(next - 1).markTask();
            System.out.println(line + "Nice! I've marked this task as done:\n" + taskList.get(next - 1) + "\n" + line);

            try {
                Alex.saveTask();
            }
            catch (IOException e) {
                System.out.println("File not found. Unable to save");
            } finally {
                echo();
            }

        } else if (firstPart.equals("unmark")) {

            if (splitter.length <= 1) {
                throw new AlexExecption("Please state which task you like to unmark");
            }

            int next = Integer.parseInt(splitter[1]);

            if (next > taskList.size() || next < 0) {
                throw new AlexExecption("Invalid number, please try again");
            }

            Task task = taskList.get(next - 1);
            task.unmarkTask();
            System.out.println(line + "Ok, I've marked this task as not done yet:\n" + task + "\n" + line);

            try {
                Alex.saveTask();
            }
            catch (IOException e) {
                System.out.println("File not found. Unable to save");
            } finally {
                echo();
            }

        } else if (firstPart.equals("todo")) {

            if (splitter.length <= 1) {
                throw new AlexExecption("Please state what you would like todo");
            }

            Task toAdd = new Todo(splitter[1]);
            taskList.add(toAdd);
            System.out.println(afterAdd1 + toAdd + "\n" + afterAdd2);

            try {
                Alex.saveTask();
            }
            catch (IOException e) {
                System.out.println("File not found. Unable to save");
            } finally {
                echo();
            }

        } else if (firstPart.equals("deadline")) {

            if (splitter.length <= 1) {
                throw new AlexExecption("Please state what deadline you have and by when");
            }

            String[] taskBreakdown = splitter[1].split(" /by ");
            String date = LocalDate.parse(taskBreakdown[1]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            Task toAdd = new Deadline(taskBreakdown[0], date);
            taskList.add(toAdd);
            System.out.println(afterAdd1 + toAdd + "\n" + afterAdd2);

            try {
                Alex.saveTask();
            }
            catch (IOException e) {
                System.out.println("File not found. Unable to save");
            } finally {
                echo();
            }

        } else if (firstPart.equals("event")) {

            if (splitter.length <= 1) {
                throw new AlexExecption("Please state when do you have the event");
            }

            String[] taskBreakdown = splitter[1].split(" / ");
            Task toAdd = new Event(taskBreakdown[0], taskBreakdown[1],taskBreakdown[2]);
            taskList.add(toAdd);
            System.out.println(afterAdd1 + toAdd + "\n" + afterAdd2);

            try {
                Alex.saveTask();
            }
            catch (IOException e) {
                System.out.println("File not found. Unable to save");
            } finally {
                echo();
            }

        } else if (firstPart.equals("delete")) {

            if (splitter.length <= 1) {
                throw new AlexExecption("Please state which task to delete");
            }

            int next = Integer.parseInt(splitter[1]);

            if (next > taskList.size() || next < 0) {
                throw new AlexExecption("Invalid number, please try again");
            }

            Task removed = taskList.remove(next - 1);
            System.out.println(line + "Noted. I've removed this task:\n" + removed + "\n" + afterDelete);

            try {
                Alex.saveTask();
            }
            catch (IOException e) {
                System.out.println("File not found. Unable to save");
            } finally {
                echo();
            }

        } else {
            throw new AlexExecption("I'm sorry, could you try with a valid prompt");
        }
    }

    public static void main(String[] args) throws AlexExecption {
        String greeting = "Hello! I'm Alex.\n";
        String action = "What can I do for you?\n";
        System.out.println(line + greeting + action + line);
        try {
            Alex.loadTask();
            echo();
        } catch (AlexExecption e) {
            System.out.println(e);
            main(args);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }
}

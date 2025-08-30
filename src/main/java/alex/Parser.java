package alex;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    private String toParse;

    public Parser(String toParse) {
        this.toParse = toParse;
    }

    public void setToParse(TaskList taskList, Storage storage) throws AlexExecption {
        String[] splitter = toParse.split(" ", 2);
        String firstPart = splitter[0];
        String afterAdd1 = Alex.LINE + "Got it. I've added this task:\n";
        String afterAdd2 = String.format("Now you have %d tasks in the list.\n", taskList.size() + 1)
                           +
                           Alex.LINE;
        String afterDelete = String.format("Now you have %d tasks in the list.\n", taskList.size() - 1)
                             +
                             Alex.LINE;
        if (firstPart.equals("bye")) {

            System.out.println(Alex.LINE + "Bye. Hope to see you again soon!\n" + Alex.LINE);

        } else if (firstPart.equals("list")) {

            System.out.println(Alex.LINE + taskList.generateTaskList() + Alex.LINE);
            new Ui().run(taskList, storage);

        } else if (firstPart.equals("mark")) {

            if (splitter.length <= 1) {
                throw new AlexExecption("Please state which task you would like to mark");
            }

            int next = Integer.parseInt(splitter[1]);

            if (next > taskList.size() || next < 0) {
                throw new AlexExecption("Invalid number, please try again");
            }

             taskList.mark(next);


            try {
                storage.saveTask(taskList);
            }
            catch (IOException e) {
                System.out.println("File not found. Unable to save");
            } finally {
                new Ui().run(taskList, storage);
            }

        } else if (firstPart.equals("unmark")) {

            if (splitter.length <= 1) {
                throw new AlexExecption("Please state which task you like to unmark");
            }

            int next = Integer.parseInt(splitter[1]);

            if (next > taskList.size() || next < 0) {
                throw new AlexExecption("Invalid number, please try again");
            }
              taskList.unmark(next);

            try {
                storage.saveTask(taskList);
            }
            catch (IOException e) {
                System.out.println("File not found. Unable to save");
            } finally {
                new Ui().run(taskList, storage);
            }

        } else if (firstPart.equals("todo")) {

            if (splitter.length <= 1) {
                throw new AlexExecption("Please state what you would like todo");
            }

            Task toAdd = new Todo(splitter[1]);
            taskList.add(toAdd);
            System.out.println(afterAdd1 + toAdd + "\n" + afterAdd2);

            try {
                storage.saveTask(taskList);
            }
            catch (IOException e) {
                System.out.println("File not found. Unable to save");
            } finally {
                new Ui().run(taskList, storage);
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
                storage.saveTask(taskList);
            }
            catch (IOException e) {
                System.out.println("File not found. Unable to save");
            } finally {
                new Ui().run(taskList, storage);
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
                storage.saveTask(taskList);
            }
            catch (IOException e) {
                System.out.println("File not found. Unable to save");
            } finally {
                new Ui().run(taskList, storage);
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
            System.out.println(Alex.LINE + "Noted. I've removed this task:\n" + removed + "\n" + afterDelete);

            try {
                storage.saveTask(taskList);
            }
            catch (IOException e) {
                System.out.println("File not found. Unable to save");
            } finally {
                new Ui().run(taskList, storage);
            }

        } else {
            throw new AlexExecption("I'm sorry, could you try with a valid prompt");
        }
    }


}

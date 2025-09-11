package alex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage of the list of tasks.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the task list into hard disk.
     *
     * @param taskList User's list of tasks.
     * @throws IOException if the file is not found.
     */
    public void saveTask(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(String.valueOf(taskList.toSaveList()));
        fw.close();
    }

    /**
     * Loads the saved tasklist from hard disk
     * during a new interaction with the chatbot.
     *
     * @return List of tasks saved previously.
     * @throws FileNotFoundException if the file is not found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<>();

        while (sc.hasNext()) {
            Task task = this.parseLine(sc.nextLine());
            taskList.add(task);
        }

        sc.close();
        return taskList;
    }

    /**
     * Converts the String form of task stored to actual task that will be loaded.
     *
     * @param line The current line of task description read from the scanner.
     * @return The task after being converted from the String form.
     */
    private Task parseLine(String line) {
        String[] parts = line.split(" / ");
        String type = parts[0];
        int taskState = Integer.parseInt(parts[1]);

        Task task = loadTask(type, parts);
        task.handleTask(taskState);
        return task;
    }

    /**
     * Loads the task based on its String representation.
     *
     * @param taskType The first of each task type.
     *                 T - Todo
     *                 E - Event
     *                 D - Deadline
     * @param parts A collection of String giving the details of the task to be loaded.
     * @return The task based on the details.
     */
    private Task loadTask(String taskType, String[] parts) {
        switch (taskType) {
        case "T":
            return new Todo(parts[2]);
        case "D":
            return new Deadline(parts[2], parts[3]);
        case "E":
            return new Event(parts[2], parts[3], parts[4]);
        default:
            throw new IllegalArgumentException("Unknown task type");
        }
    }

}

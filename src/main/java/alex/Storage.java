package alex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage of the list of tasks
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the task list into hard disk
     * @param taskList User's list of tasks
     * @throws IOException if the file is not found
     */
    public void saveTask(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(String.valueOf(taskList.toSaveList()));
        fw.close();
    }

    /**
     * Loads the saved tasklist from hard disk
     * during a new interaction with the chatbot
     * @return List of tasks saved previously
     * @throws FileNotFoundException if teh file is nto found
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<>();
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
        return taskList;
    }
}

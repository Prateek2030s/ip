package alex;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks stored
 */
public class TaskList {
    List<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = null;
    }

    /**
     * Marks the task as done
     * @param index Position of task in the list
     */
    public void mark(int index) {
        taskList.get(index - 1).markTask();
        System.out.println(Alex.LINE + "Nice! I've marked this task as done:\n" + taskList.get(index - 1)
                + "\n" + Alex.LINE);
    }

    /**
     * Marks the task as undone
     * @param index Position of task in the list
     */
    public void unmark(int index) {
        Task task = taskList.get(index - 1);
        task.unmarkTask();
        System.out.println(Alex.LINE + "Ok, I've marked this task as not done yet:\n" + task + "\n" + Alex.LINE);
    }

    /**
     * Adds task into the list
     * @param task Task to be added
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes task from list
     * @param index Position of task to be removed in the list
     * @return
     */
    public Task remove(int index) {
        return taskList.remove(index);
    }

    public String findMatch(String find) {
        String ans = Alex.LINE + "Here are the matching tasks in your list:\n";
        int i = 1;
        for (Task task : taskList) {
            if (task.toString().contains(find)) {
                ans = ans + i + "." + task + "\n";
                i++;
            }
        }
        ans = ans + Alex.LINE;
        return ans;
    }

    /**
     * List length
     * @return List length
     */
    public int size() {
        return taskList.size();
    }


    /**
     * List representation
     * @return List representation
     */
    public String generateTaskList() {
        int i = 1;
        String ans = "Here are the tasks in your list:\n";
        for (Task t : taskList) {
            ans = ans + i + "." + t + "\n";
            i++;
        }

        return ans;
    }

    public String toSaveList() {
        StringBuilder s = new StringBuilder();
        for (Task t : taskList) {
            s.append(t.toFileString()).append(System.lineSeparator());
        }
        return String.valueOf(s);
    }
}

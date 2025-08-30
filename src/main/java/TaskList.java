import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = null;
    }

    public void mark(int index) {
        taskList.get(index - 1).markTask();
        System.out.println(Alex.LINE + "Nice! I've marked this task as done:\n" + taskList.get(index - 1)
                + "\n" + Alex.LINE);
    }

    public void unmark(int index) {
        Task task = taskList.get(index - 1);
        task.unmarkTask();
        System.out.println(Alex.LINE + "Ok, I've marked this task as not done yet:\n" + task + "\n" + Alex.LINE);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task remove(int index) {
        return taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }

    public String fullTaskList() {
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

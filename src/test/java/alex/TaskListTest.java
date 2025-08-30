package alex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class TaskListTest {

    @Test
    public void toSaveList_correctFormat() {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("sleep"));
        list.add(new Todo("eat"));
        String s = """
                T / 1 / sleep
                T / 1 / eat
                """;
        assertEquals(s, new TaskList(list).toSaveList());
    }
}

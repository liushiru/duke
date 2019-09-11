package seedu.duke;

import java.util.ArrayList;

/**
 * Represents the task list
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Returns the content in the task list
     *
     * @return a string that display every task in the lists
     */
    @Override
    public String toString() {
        String content = "";
        int i = 1;
        for (Task task : this) {
            content += i++ + "." + task.toString() + "\n";
        }
        return content;
    }
}

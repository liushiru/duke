package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.UI;

/**
 * Represents a command to list all tasks in the task list
 */
public class ListCommand extends Command {

    /**
     * Display all tasks in the task list
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.displayTasks(tasks);
    }
}

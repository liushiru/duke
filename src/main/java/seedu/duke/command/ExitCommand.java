package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.UI;

/**
 * Represents a command to exit main programme
 */
public class ExitCommand extends Command {


    /**
     * Displays farewell message
     *
     * @param tasks The TaskList object that stores all tasks in the list
     * @param ui The user interface object that displays text response to user's input
     * @param storage The seedu.duke.Storage object that controls the text file that stores tasks
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.exit();
    }

}

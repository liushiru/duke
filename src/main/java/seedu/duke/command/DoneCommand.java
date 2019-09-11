package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.UI;

/**
 * Represents the command to mark a task as done
 */
public class DoneCommand extends Command {

    /**
     * Represent the task number of the task to be marked as done.
     */
    protected int taskNum;

    /**
     * Creates a new DoneCommand with the task number of the task that needs to be marked as done
     *
     * @param taskNum Task number of the task to be marked as done
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks the task as done
     *
     * @param tasks   The TaskList object that stores all tasks in the list
     * @param ui      The user interface object that displays text response to user's input
     * @param storage The seedu.duke.Storage object that controls the text file that stores tasks
     * @throws DukeException.taskOutOfRangeException
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException.taskOutOfRangeException {
        if (taskNum >= tasks.size() || taskNum < 0) {
            throw new DukeException.taskOutOfRangeException();
        }
        tasks.get(this.taskNum).setDone();
        storage.writeFile(tasks);
        ui.doneTaskUI(tasks.get(this.taskNum));
    }
}
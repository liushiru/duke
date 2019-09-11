package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.UI;

/**
 * Represents a command to delete a task
 */
public class DeleteCommand extends Command {

    /**
     * Represent the task number of the task to be deleted.
     */
    protected int taskNum;

    /**
     * Creates a new DeleteCommand with the task number (1 based) of task that needs to be deleted
     *
     * @param taskNum The task number of the task that needs to be deleted
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Deletes the task specified from the task list and the storage file
     *
     * @param tasks   The TaskList object that stores all tasks in the list
     * @param ui      The user interface object that displays text response to user's input
     * @param storage The seedu.duke.Storage object that controls the text file that stores tasks
     * @throws DukeException.taskOutOfRangeException Throws exception when the task number is out of range
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException.taskOutOfRangeException {
        if (taskNum >= tasks.size() || taskNum < 0) {
            throw new DukeException.taskOutOfRangeException();
        }
        tasks.remove(this.taskNum);
        ui.deleteTaskUI(this.taskNum);
        ui.displayNumOfTasks(tasks);
        storage.writeFile(tasks);
    }
}

package seedu.duke.command;

import seedu.duke.*;

/**
 * Represents a command to add new task
 */
public class AddCommand extends Command {

    /**
     * Represents the task to be added
     */
    Task task;

    /**
     * Creates a new AddCommand with the given task
     *
     * @param task The task to add
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes this command by
     * <li>
     * adding the task into the given task list
     * </li>
     * <li>
     * displaying response to show user task are added
     * </li>
     * <li>
     * store the task to the file
     * </li>
     *
     * @param tasks   The TaskList object that stores all tasks in the list
     * @param ui      The user interface object that displays text response to user's input
     * @param storage The seedu.duke.Storage object that controls the text file that stores tasks
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.add(this.task);
        ui.addTaskUI(this.task);
        ui.displayNumOfTasks(tasks);
        storage.writeFile(tasks);
    }

}

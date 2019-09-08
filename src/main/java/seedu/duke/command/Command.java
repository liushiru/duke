package seedu.duke.command;


import seedu.duke.*;

/**
 * Abstract class representing a generic command
 */

public abstract class Command {
    /**
     * Execute this command
     * @param tasks The TaskList object that stores all tasks in the list
     * @param ui The user interface object that displays text response to user's input
     * @param storage The seedu.duke.Storage object that controls the text file that stores tasks
     * @throws DukeException.taskOutOfRangeException
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException.taskOutOfRangeException;

}

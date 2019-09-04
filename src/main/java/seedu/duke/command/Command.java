package seedu.duke.command;


import seedu.duke.*;

public abstract class Command {

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException.taskOutOfRangeException;

}

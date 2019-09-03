package seedu.duke.command;


import seedu.duke.CommandType;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.UI;

public abstract class Command {

    public abstract void execute(TaskList tasks, UI ui, Storage storage);

}

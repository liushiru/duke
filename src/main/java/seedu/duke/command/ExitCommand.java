package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.UI;

public class ExitCommand extends Command {

    protected boolean isExit;

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.exit();
    }

    public boolean isExit() {
        return true;
    }
}

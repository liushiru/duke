package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.UI;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        System.out.println(tasks.displayContent(tasks));

    }
}

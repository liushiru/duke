package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.UI;

public class DoneCommand extends Command{

    protected int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.get(this.taskNum).setDone();
        storage.writeFile(tasks);
        ui.doneTaskUI(tasks.get(this.taskNum));
    }
}
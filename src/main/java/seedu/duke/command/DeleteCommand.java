package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.UI;

public class DeleteCommand extends Command{

    protected int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.remove(this.taskNum);
        ui.deleteTaskUI(this.taskNum);
        ui.displayNumOfTasks(tasks);
        storage.writeFile(tasks);
    }
}

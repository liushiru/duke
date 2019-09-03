package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Task;
import seedu.duke.TaskList;
import seedu.duke.UI;


public class FindCommand extends Command {

    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        TaskList relevantTasks = new TaskList();
        for(int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.toString().contains(this.keyword)) {
                relevantTasks.add(currTask);
            }
        }
        ui.findTaskUI(relevantTasks);
    }
}

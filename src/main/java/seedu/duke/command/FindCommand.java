package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Task;
import seedu.duke.TaskList;
import seedu.duke.UI;

/**
 * Represents a command to find all tasks with the given keywords
 */
public class FindCommand extends Command {

    protected String keyword;

    /**
     * Creates a new FindCommand with the given keywords
     * @param keyword The keyword that needs to be searched
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds all relevant tasks with the keyword and store them into a new temporary task lists
     *
     * @param tasks The TaskList object that stores all tasks in the list
     * @param ui The user interface object that displays text response to user's input
     * @param storage The seedu.duke.Storage object that controls the text file that stores tasks
     */
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

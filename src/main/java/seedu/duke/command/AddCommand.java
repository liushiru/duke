package seedu.duke.command;

import seedu.duke.*;

import java.io.FileNotFoundException;

public class AddCommand extends Command {

    Task task;
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage)  {
        EventType eventType = this.task.getType();

        switch (eventType) {
            case D: {
                tasks.add(this.task);
                ui.addTaskUI(this.task);
                storage.writeFile(tasks);
            }
        }
    }

}

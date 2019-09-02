package seedu.duke.command;


import seedu.duke.CommandType;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.UI;

public abstract class Command {

   // protected CommandType type;

//    public Command(CommandType c) {
//        this.type = c;
//    }

    public abstract void execute(TaskList tasks, UI ui, Storage storage);



}

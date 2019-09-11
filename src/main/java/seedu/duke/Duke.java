package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;

import java.io.IOException;


/**
 * Duke is a personal assistance that helps to keep track of the tasks you have There are three types of task:
 * Todo, Deadline, Event
 *
 * @author liushiru
 */
public class Duke {

    /**
     * Manages storage to the text file
     */
    private Storage storage;

    /**
     * A List to store tasks
     */
    private TaskList tasks;

    /**
     * Control all user interaction
     */
    private UI ui;

    /**
     * Creates a duke class
     *
     * @param filePath The file path that all tasks are stored
     */
    public Duke(String filePath) {
        ui = new UI();
        try {
            storage = new Storage(filePath);
            tasks = storage.readFile();
        } catch (Exception e) {
            tasks = new TaskList();
            e.printStackTrace();
        }
    }

    /**
     * Run the programme
     */
    public void run() {
        ui.start();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                if (c instanceof ExitCommand) {
                    isExit = true;
                }
                // isExit = fullCommand.equals("bye");
            } catch (DukeException e) {
                e.printMessage();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("duke.txt").run();
    }
}


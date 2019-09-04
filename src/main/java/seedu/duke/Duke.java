package seedu.duke;

import seedu.duke.command.Command;
import java.io.*;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

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

    public void run() {
        ui.start();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = fullCommand.equals("bye");
              //  isExit = c.isExit();
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


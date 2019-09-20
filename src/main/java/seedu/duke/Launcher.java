package seedu.duke;

import javafx.application.Application;
import seedu.duke.ui.Main;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Duke.setFilePath(args[0]);
        Application.launch(Main.class, args);
    }
}
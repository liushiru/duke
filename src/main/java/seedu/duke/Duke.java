package seedu.duke;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javafx.scene.image.Image;

/**
 * Duke is a personal assistance that helps to keep track of the tasks you have There are three types of task:
 * Todo, Deadline, Event
 *
 * @author liushiru
 */
public class Duke {
    private static String filePath;

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

    /////////////////////////
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    ////////////////////////

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }



    /**
     * Creates a duke class
     *
     */
    public Duke() {

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
     * Runs the programme.
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
            } catch (DukeException e) {
                e.printMessage();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        outContent.reset();
        setUpStreams();
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            restoreStreams();
            return outContent.toString();
        } catch (DukeException dukeException) {
            dukeException.printMessage();
            return dukeException.getMessage();
        }
    }


    public static void setFilePath(String path) {
        filePath = path;
    }
}


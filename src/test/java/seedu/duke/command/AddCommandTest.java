package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Storage;
import seedu.duke.Task;
import seedu.duke.TaskList;
import seedu.duke.Todo;
import seedu.duke.UI;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class AddCommandTest {


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


    @Test
    void executeTest_addSuccessful() throws IOException {
        setUpStreams();
        TaskList tasks = new TaskList();
        UI ui = new UI();
        Storage storage = new Storage("test.txt");
        Task task = new Todo("read book");
        AddCommand addCommand = new AddCommand(task);
        addCommand.execute(tasks, ui, storage);

        TaskList testTaskList = new TaskList();
        testTaskList = storage.readFile();
        boolean assertion =
                (testTaskList.get(testTaskList.size() - 1).toString().equals(task.toString()));
        assertTrue(assertion);
        String expected =
                "Got it. I've added this task: \r\n"
                + "  [T][✗] read book\r\n"
                + "Now you have 1 tasks in the list\r\n";

        restoreStreams();
        assertEquals(expected, outContent.toString());
    }
}

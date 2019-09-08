package seedu.duke;

/**
 * Represents all Exceptions that specific to Duke
 */
public class DukeException extends Exception {

    protected String message;

    /**
     * Creates a DukeException with the given message
     * @param input Message the exception wants to deliver
     */
    public DukeException(String input) {
        this.message = input;
    }

    /**
     * Print the message of the exception
     */
    public void printMessage() {
        System.out.println(this.message);
    }

    /**
     * Represents a invalid input exception
     */
    public static class inputInvalidException extends DukeException {
        public inputInvalidException() {
            super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Represents a emptyDescription exception
     */
    public static class emptyDescriptionException extends DukeException {
        public emptyDescriptionException() {
            super("☹ OOPS!!! The description cannot be empty.");
        }
    }

    /**
     * Represents a invalid task number exception
     */
    public static class invalidTaskNumException extends DukeException {
        public invalidTaskNumException() {
            super("☹ OOPS!!! We don't know which task to process, pls enter an task number as a integer");
        }
    }

    /**
     * Represents a exception when user does not key in "time" component for event and deadline
     */
    public static class missingTimeException extends DukeException {
        public missingTimeException() {
            super("Please enter timing for your task");
        }
    }

    /**
     * Represents a exception when task number is out of the range
     */
    public static class taskOutOfRangeException extends DukeException {
        public taskOutOfRangeException() {
            super("☹ OOPS!!! You don't have such task in your list");
        }
    }
/*    public static class invalidCommandType extends DukeException {
        public invalidCommandType() {
            super("☹ OOPS!!! the command type is invalid");
        }
    }*/


    /**
     * Represents a input with valid command word but invalid formatting
     */
    public static class invalidFormatException extends DukeException {
        public invalidFormatException() {
            super("☹ OOPS!!! The format is invalid");
        }
    }

    public static class emptyFileException extends DukeException {
        public emptyFileException() {
            super("☹ OOPS!!! The file is empty");
        }
    }

}

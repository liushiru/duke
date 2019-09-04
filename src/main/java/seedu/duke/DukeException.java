package seedu.duke;

public class DukeException extends Exception {
   // public static Object inputInvalidException;
    //public static Object inputInvalidException;
   // public static Object emptyDescriptionException;
    protected String message;

    public DukeException(String input) {
        this.message = input;
    }

    public void printMessage() {
        System.out.println(this.message);
    }

    public static class inputInvalidException extends DukeException {
        public inputInvalidException() {
            super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static class emptyDescriptionException extends DukeException {
        public emptyDescriptionException() {
            super("☹ OOPS!!! The description cannot be empty.");
        }
    }

    public static class invalidTaskNumException extends DukeException {
        public invalidTaskNumException() {
            super("☹ OOPS!!! We don't know which task to process, pls enter an task number as a integer");
        }
    }

    public static class missingTimeException extends DukeException {
        public missingTimeException() {
            super("Please enter timing for your task");
        }
    }

    public static class taskOutOfRangeException extends DukeException {
        public taskOutOfRangeException() {
            super("☹ OOPS!!! You don't have such task in your list");
        }
    }
    public static class invalidCommandType extends DukeException {
        public invalidCommandType() {
            super("☹ OOPS!!! the command type is invalid");
        }
    }

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

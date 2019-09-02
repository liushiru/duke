package seedu.duke;

public class DukeException extends Exception {
   // public static Object inputInvalidException;
    //public static Object inputInvalidException;
   // public static Object emptyDescriptionException;
    protected String input;

    public DukeException(String input) {
        this.input = input;
    }

    public static class inputInvalidException extends Exception {
        public inputInvalidException() {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static class emptyDescriptionException extends Exception {
        public emptyDescriptionException(String type) {
            System.out.println("☹ OOPS!!! The description of a " + type + " cannot be empty.");
        }
    }

    public static class invalidTaskNumException extends Exception {
        public invalidTaskNumException() {
            System.out.println("☹ OOPS!!! We don't know which task to process, pls enter an task number as a integer");
        }
    }

    public static class taskOutOfRangeException extends Exception {
        public taskOutOfRangeException() {
            System.out.println("☹ OOPS!!! You don't have such task in your list");
        }
    }
    public static class invalidCommandType extends Exception {
        public invalidCommandType() {
            System.out.println("☹ OOPS!!! the command type is invalid");
        }
    }
    public static class emptyFileException extends Exception {
        public emptyFileException() {
            System.out.println("☹ OOPS!!! the command type is invalid");
        }
    }

}

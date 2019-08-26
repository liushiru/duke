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
}

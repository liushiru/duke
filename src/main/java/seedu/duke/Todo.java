package seedu.duke;

/**
 * Represents a task of type Todo.
 */
public class Todo extends Task {

    /**
     * Instantiate a Todo Object with the given description.
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string describing the todo in desired format.
     *
     * @return The string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] " + super.getDescription();
    }
}

package seedu.duke;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon()+"]" + "[D] " + super.getDescription() + " (by: " + by + ")";
    }
}
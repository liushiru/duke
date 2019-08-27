package seedu.duke;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getTime() {
        return this.by;
    }
    @Override
    public String toString() {
        return "[D]"+"[" + super.getStatusIcon()+"] " + super.getDescription() + " (by: " + by + ")";
    }
}
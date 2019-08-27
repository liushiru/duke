package seedu.duke;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getTime() {
        return this.at;
    }
    @Override
    public String toString() {
        return "[" + super.getStatusIcon()+"]" + "[E] " + super.getDescription() + " (at: " + at + ")";
    }
}

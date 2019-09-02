package seedu.duke;

import java.util.Date;

public class Deadline extends Task {

    protected String by;
    protected Date time;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.setType(EventType.D);
    }

    public Date getTime() {
        return this.time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    @Override
    public String toString() {
        return "[D]"+"[" + super.getStatusIcon()+"] " + super.getDescription() + " (by: " + by + ")";
    }
}
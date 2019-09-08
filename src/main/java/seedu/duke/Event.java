package seedu.duke;

import java.util.Date;

public class Event extends Task {
    protected String at;
    protected Date time;
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.setType(TaskType.E);
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon()+"] "  + super.getDescription() + " (at: " + at + ")";
    }
}

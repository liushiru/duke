package seedu.duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected EventType type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return this.type;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✗"); //return tick or X symbols
    }

    public void setDone() {
        this.isDone = true;
    }

}
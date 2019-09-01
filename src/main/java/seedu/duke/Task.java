package seedu.duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

enum Type {
    T,
    D,
    E
}

public class Task {
    protected String description;
    protected boolean isDone;
    protected Type type;

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

    public void setType(Type type) {
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✗"); //return tick or X symbols
    }

    public void setDone() {
        this.isDone = true;
    }

}
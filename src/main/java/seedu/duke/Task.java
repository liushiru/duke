package seedu.duke;

/**
 * Represents a generic task
 */
public class Task {

    /**
     * Represents the description component of the task.
     */
    protected String description;

    /**
     * Represents the whether the tasks is marked as done
     */
    protected boolean isDone;

    /**
     * Creates a task with the given description. Status is "not done" by default
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of this task
     *
     * @return Description of this task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of this tasks.
     *
     * @return True when task is done, False when it is not
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns the status icon string of the task
     *
     * @return "✓" if task is done, "✗" otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "✗"); //return tick or X symbols
    }

    /**
     * Marks the task as done
     */
    public void setDone() {
        this.isDone = true;
    }

}
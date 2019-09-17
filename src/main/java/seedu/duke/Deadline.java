package seedu.duke;

import java.util.Date;

/**
 * Represents a task of type deadline.
 */
public class Deadline extends Task {

    /**
     * Represents the deadline time in String type.
     */
    protected String by;

    /**
     * Represents the deadline time in Date type.
     */
    protected Date time;

    /**
     * Creates a deadline.
     *
     * @param description Description of the deadline.
     * @param by          Time of the deadline as a string.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the time of the deadline as a Date.
     *
     * @return the time of the deadline.
     */
    public Date getTime() {
        return this.time;
    }

    /**
     * Set the time of the deadline.
     *
     * @param time the time of the deadline.
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Returns a string representing this deadline.
     *
     * @return A string representing this deadline.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (by: " + by + ")";
    }
}
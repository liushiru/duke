package seedu.duke;

import java.util.Date;

/**
 * Represents a task of type Event
 */
public class Event extends Task {

    /**
     * Event time in String type.
     */
    protected String at;

    /**
     * Event time in Date type.
     */
    protected Date time;

    /**
     * Creates a new event with the given description and time
     *
     * @param description Description of the event
     * @param at          Time of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the time of the event as a Date
     *
     * @return the time of the event
     */
    public Date getTime() {
        return this.time;
    }

    /**
     * Set the time of the event
     *
     * @param time Time of the event
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Returns a string describing the event in desired format
     *
     * @return The string representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (at: " + at + ")";
    }
}

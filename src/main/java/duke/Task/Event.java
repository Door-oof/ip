package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is an event with both start and end time/date.
 */
public class Event extends Task {

    protected String at;

    private final LocalDate startDate;
    private LocalDate endDate;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public static final String delimiterAt = " /at ";

    /**
     * Constructs an <code>Event</code> Object to represent an event.
     *
     * @param description The description of the event item.
     * @param at          The duration of the event (including the start and end of both date and time).
     * @throws DukeException If the format of either date or time is incorrect.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;

        String[] dateTime = this.at.split(" ");

        // case if event last for day.
        if (dateTime[0].contains("-")) {
            String[] dateSplit = dateTime[0].split("-");
            this.startDate = parseDate(dateSplit[0]);
            this.endDate = parseDate(dateSplit[1]);

            if (this.startDate.compareTo(this.endDate) > 0) {
                throw new DukeException("The start date cannot be after the end date.");
            }
        } else {
            this.startDate = parseDate(dateTime[0]);
        }

        String[] timeSplit = dateTime[1].split("-");
        this.startTime = parseTime(timeSplit[0]);
        this.endTime = parseTime(timeSplit[1]);

        if (this.startTime.compareTo(this.endTime) > 0) {
            throw new DukeException("The start time cannot be after the end time.");
        }

    }

    /**
     * Parses a text and returns the date of an event.
     *
     * @param dateString The text to be parsed.
     * @return The date of an event.
     */
    private LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    /**
     * Parses a text and returns the time of an event.
     *
     * @param timeString The text to be parsed.
     * @return The time of an event.
     */
    private LocalTime parseTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Hmm");
        return LocalTime.parse(timeString, formatter);
    }

    private String atFormat() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE, d MMMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");
        if (endDate == null) {
            return String.format("%s, %s - %s", dateFormatter.format(startDate),
                    timeFormatter.format(startTime),
                    timeFormatter.format(endTime));
        } else {
            return String.format("%s - %s, %s - %s", dateFormatter.format(startDate),
                    dateFormatter.format(endDate),
                    timeFormatter.format(startTime),
                    timeFormatter.format(endTime));
        }
    }

    @Override
    public String serialize() {
        return String.format("E | %d | %s | %s", getStatusCode(), description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atFormat() + ")";

    }
}
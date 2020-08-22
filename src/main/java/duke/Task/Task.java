package duke.Task;

import duke.Exception.DukeException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a <code>Task</code> Object to represent a task
     *
     * @param description The description of a task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    public int getStatusCode() {
        return this.isDone ? 1 : 0;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the status of a task as done
     *
     * @throws DukeException If a task is already marked as done before
     */
    public void markAsDone() throws DukeException {
        if (isDone) {
            throw new DukeException("This task is already completed!");
        }
        this.isDone = true;
    }

    public abstract String serialize();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}
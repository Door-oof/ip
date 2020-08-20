package duke;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String serialize() {
        return String.format("T | %d | %s", getStatusCode(), description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
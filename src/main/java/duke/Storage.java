package duke;

import java.util.Scanner;

import java.util.ArrayList;

import duke.exception.DukeException;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles loading tasks from the given file and saving tasks to the same file.
 */
public class Storage {
    
    private String filePath;

    public static final String STORAGE_FILEPATH = "duke.txt";

    /**
     * Constructs a <code>Storage</code> Object using filePath.
     *
     * @param filePath The filePath where the data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the existing tasks from the data file.
     *
     * @return An ArrayList of tasks which are stored in the data file.
     * @throws DukeException If file does not exist.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(" \\| ");
                Task task;

                switch (data[0]) {
                case "T":
                    task = new ToDo(data[2]);
                    break;
                case "D":
                    task = new Deadline(data[2], data[3]);
                    break;
                case "E":
                    task = new Event(data[2], data[3]);
                    break;
                default:
                    throw new DukeException("Failed to load tasks.");
                }

                if (data[1].equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
            scanner.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File is not found");
        }
    }

    /**
     * Saves the current tasks in the list to the data file.
     *
     * @param tasks The TaskList to be saved to the destined filePath.
     * @throws DukeException If writing to file fails.
     */
    public void saveTasks(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                fw.write(task.serialize());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to save tasks");
        }
    }
}

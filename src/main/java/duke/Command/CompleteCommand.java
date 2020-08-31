package duke.command;

import duke.Storage;

import duke.exception.DukeException;

import duke.task.Task;
import duke.task.TaskList;

import duke.ui.Message;
import duke.ui.Ui;

/**
 * Marks a task as done.
 */
public class CompleteCommand extends Command {

    private final int index;

    public CompleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.get(index);
        task.markAsDone();
        storage.saveTasks(taskList);
        return Message.concatLines(Message.MESSAGE_DONE, task.toString());
    }
}
package duke.Command;

import duke.Command.Command;
import duke.Duke;

public class ListCommand extends Command {

    public String execute() {

        StringBuilder list = new StringBuilder();

        if (Duke.listArray.isEmpty()) {
            return "list is empty";
        } else {
            for (int i = 1; i <= Duke.listArray.size(); i++) {
                if (i == Duke.listArray.size()) {
                    list.append(i).append(".").append(Duke.listArray.get(i - 1).toString());
                } else {
                    list.append(i).append(".").append(Duke.listArray.get(i - 1).toString())
                            .append("\n");
                }
            }
            return "Here are the tasks in your list:\n" + list.toString();
        }
    }
}

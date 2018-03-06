/**
 * Holds both a list of task buttons that are used to start and end
 * tasks, as well as a list of tasks, which can be started and ended.
 */

package finalProject;

import java.util.LinkedList;
import java.util.List;

public class TaskButtonHolder {
    private List<TaskButton> taskButtons = new LinkedList<>();
    private TaskHolder taskHolder = new TaskHolder();

    //No constructor needed.

    /**
     * this is the meat of the task creation. It creates a task and a task
     * button.
     * @param name the name of the task / task button
     */
    public void addTaskButton(String name) {
        int taskNumber = this.size();
        TaskButton taskButton = new TaskButton(name);
        taskButton.setOnAction(e -> this.taskHolder.changeTaskStates(taskNumber));
        taskButtons.add(taskButton);
        taskHolder.addTask(name);
    }

    public int size() {
        return this.taskButtons.size();
    }

    public TaskButton get(int index) {
        try {
            return this.taskButtons.get(index);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("get: No TaskButton at this index.");

            //what do you do here?? It is making me return something!
            return null;
        }
    }
}
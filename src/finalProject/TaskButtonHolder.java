package finalProject;

import java.util.LinkedList;
import java.util.List;

public class TaskButtonHolder {
    private List<TaskButton> taskButtons = new LinkedList<>();
    private TaskHolder taskHolder = new TaskHolder();

    //No constructor needed.

    public void addTaskButton(String name) {
        int taskNumber = this.size();
        TaskButton taskButton = new TaskButton(name);
        taskButton.setOnAction(e -> this.taskHolder.changeTaskStates(taskNumber));
        taskButtons.add(taskButton);
        taskHolder.addTask(name);
    }

    public void remove(int taskNumber) {
        try {
            taskButtons.remove(taskNumber);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("removeTaskButton: No TaskButton at this index.");
        }
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
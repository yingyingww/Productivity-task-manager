package finalProject;

import java.util.LinkedList;
import java.util.List;

public class TaskHolder {
    private List<Task> tasks  = new LinkedList<>();
    private int currentRunningTask = -1;

    //No constructor needed.

    public void addTask(String name) {
        tasks.add(new Task(name));
    }

    public void remove(int taskNumber) {
        try {
            tasks.remove(taskNumber);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("removeTask: No Task at this index.");
        }
    }

    public void setCurrentRunningTask(int taskNumber) {
        currentRunningTask = taskNumber;
    }

    public void changeTaskStates(int taskNumber) {
        // what if it's empty?
        if (currentRunningTask == -1) {
            setCurrentRunningTask(taskNumber);
        } else if (currentRunningTask == taskNumber) {
            setCurrentRunningTask(-1);
        } else {
            this.tasks.get(currentRunningTask).changeTaskState();
            setCurrentRunningTask(taskNumber);
        }
        this.tasks.get(taskNumber).changeTaskState();
    }
}
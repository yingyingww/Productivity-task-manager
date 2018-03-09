package finalProject;

import java.util.LinkedList;
import java.util.List;

public class TaskHolder {
    private List<Task> tasks  = new LinkedList<>();
    private int currentRunningTask = -1;
    Main m;

    //No constructor needed.

    public boolean contains(String name) {
        for (int i=0; i<tasks.size(); i++) {
            if (tasks.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void setMain(Main m) {
        this.m = m;
    }

    public void addTask(String name) {
        tasks.add(new Task(name, this.m));
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

    /**
     * The meat of starting and ending tasks, makes sure that an old task is
     * ended and a new task begins.
     * @param taskNumber the identification of each task (and its button)
     *                   based on its position in the taskHolder
     */
    public void changeTaskStates(int taskNumber) {
        // TODO: what if it's empty?
        if (currentRunningTask == -1) {
            // if no task is running
            setCurrentRunningTask(taskNumber);
        } else if (currentRunningTask == taskNumber) {
            // if the current task is already running,
            // turn it off
            setCurrentRunningTask(-1);
        } else {
            // used to switch tasks
            this.tasks.get(currentRunningTask).changeState();
            setCurrentRunningTask(taskNumber);
        }
        this.tasks.get(taskNumber).changeState();
    }
}

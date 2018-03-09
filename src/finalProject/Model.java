package finalProject;

import java.util.HashSet;

public class Model {
    private HashSet<Task> tasks = new HashSet<>();
    private Task currentTask;

    public void addTask(String name) throws EmptyTaskNameException, taskAlreadyExistsException {
        if (name.isEmpty()) {
            throw new EmptyTaskNameException("Please choose a task name.");
        } else if (taskExists(name)){
            throw new taskAlreadyExistsException("This task already exists.");
        } else {
            tasks.add(new Task(name)); // have to change task constructor
        }
    }

    public void switchTasks(Task newCurrent) {
        if (currentTask == null) {
            currentTask = newCurrent;
        } else if (newCurrent.equals(currentTask)) {
            currentTask = null;
        } else {
            currentTask.changeState();
            currentTask = newCurrent;
        }
        newCurrent.changeState();
    }

    private boolean taskExists(String name) {
        // check to see if the case is the same!!
        return tasks.contains(name);
    }



}

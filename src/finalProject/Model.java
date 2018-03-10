package finalProject;

import java.util.HashMap;

public class Model {
    private HashMap<String, Task> tasks = new HashMap<>();
    private Task currentTask;

    public void addTask(String name) throws EmptyTaskNameException, taskAlreadyExistsException {
        if (name.isEmpty()) {
            throw new EmptyTaskNameException("Please choose a task name.");
        } else if (taskExists(name)){
            throw new taskAlreadyExistsException("This task already exists.");
        } else {
            tasks.put(name, new Task(name)); // have to change task constructor
        }
    }

    public void switchTasks(Task newCurrent) {
        newCurrent.changeState();
        if (currentTask == null) {
            currentTask = newCurrent;
        } else if (newCurrent.equals(currentTask)) {
            currentTask = null;
            // ask for productivity!
        } else {
            currentTask.changeState();
            currentTask = newCurrent;
        }
    }

    private boolean taskExists(String name) {
        // check to see if the case is the same!!
        return tasks.containsKey(name);
    }

    public Task getTask(String name) {
        return this.tasks.get(name);
    }

}

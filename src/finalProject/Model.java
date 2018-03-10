package finalProject;

import java.util.HashMap;

public class Model {
    private HashMap<String, Task> tasks = new HashMap<>();
    private Task currentTask;
    private Controller2 controller;

    public Model(Controller2 controller) {
        this.controller = controller;
    }

    public void addTask(String name) throws EmptyTaskNameException, taskAlreadyExistsException {
        if (name.isEmpty()) {
            throw new EmptyTaskNameException("Please choose a task name.");
        }
        name = name.toLowerCase();
        if (taskExists(name)){
            throw new taskAlreadyExistsException("This task already exists.");
        } else {
            tasks.put(name, new Task(name, controller)); // have to change task constructor
        }
    }

    public void switchTasks(Task newCurrent) {
        newCurrent.changeState();
        if (currentTask == null) {
            currentTask = newCurrent;
        } else if (newCurrent.equals(currentTask)) {
            currentTask = null;
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
        name = name.toLowerCase();
        return this.tasks.get(name);
    }

}

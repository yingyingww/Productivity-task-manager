package finalProject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
    private String name;
    private boolean isRunning = false;
    private Timer t = new Timer();
    private List<TaskInstance> TaskInstances = new ArrayList<>();
    private Controller2 controller;

    public Task(String name, Controller2 controller) {
        this.name = name;
        this.controller = controller;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Calls startTask() if its not running and endTask() if it is.
     */
    public void changeState() {
        if (!this.isRunning) {
            startTask();
        } else {
            endTask();
        }
        this.isRunning = !this.isRunning;
    }

    // The following two methods will be used to call other classes.
    private void startTask() {
        t.logStartTime();
    }

    private void endTask() {
        t.logEndTime();
        Date start = t.getStartTime();
        Date end = t.getEndTime();
        int productivity = getProductivity();
        System.out.println("Task " + name + "\nstarted: " + start + "\nended: " + end + "\nproductivity: " + productivity);
        TaskInstances.add(new TaskInstance(start, end, productivity));
    }

    public int getProductivity() {
        return controller.getProductivity(this.getName());
    }

}
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
    private int totalTimeSpent;

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
        int duration = t.getTimeInterval();
        int productivity = getProductivity();
        totalTimeSpent += duration;
        System.out.println("Task " + name + "\nstarted: " + start + "\nended: " + end + "\nproductivity: " + productivity);
        TaskInstances.add(new TaskInstance(start, end, productivity, duration));
    }

    public int getProductivity() {
        return controller.getProductivity(this.getName());
    }
    
    public int getAvgProductivity() {
        int avgProductivity = 0;
        for (TaskInstance instance : TaskInstances) {
            if (instance.getProductivity() != -1) {
                avgProductivity += instance.getProductivity();
            }
        }
        return (avgProductivity / TaskInstances.size());
    }

    public int getTotalTimeSpent(){
        return this.totalTimeSpent;
    }


}

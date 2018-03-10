package finalProject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
    private String name;
    private boolean isRunning = false;
    private Timer t = new Timer();
    private List<List<Date>> Instances = new ArrayList<>();

    public Task(String name) {
        this.name = name;
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
        String beginTaskStatement = "Task " + this.getName() + " has begun.";
        System.out.println(beginTaskStatement);
        t.logStartTime();
    }

    private void endTask() {
        String endTaskStatement = "Task " + this.getName() + " has ended.";
        System.out.println(endTaskStatement);
        t.logEndTime();
        this.addInstance();
    }

    private void addInstance() {
        List<Date> Instance = new ArrayList<>();
        Instance.add(t.getStartTime());
        Instance.add(t.getEndTime());
        Instances.add(Instance);
    }
}
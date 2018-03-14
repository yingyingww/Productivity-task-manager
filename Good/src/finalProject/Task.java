package finalProject;

import java.util.*;
/**
 * The Task java file holds information about Tasks to be sent to controller and used by the model
 *
 */
public class Task {
    private String name;
    private boolean isRunning = false;
    private Timer t = new Timer();
    private List<TaskOccurrence> taskOccurrences = new ArrayList<>();
    private Controller controller;
    private int totalTimeSpent;

    public Task(String name, Controller controller) {
        this.name = name;
        this.controller = controller;
    }

    public String getName() {
        return this.name;
    }
    
    public List<TaskOccurrence> getTaskOccurrences() {
        return taskOccurrences;
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
        // Updates the current calendar when a task is ended with a information to create a rectangle
        controller.updateCurrentCalendar(name, start, end);
        // Adds to the list of occurrences
        taskOccurrences.add(new TaskOccurrence(start, end, productivity, duration));

    }

    public int getTotalTimeSpent(){
        return this.totalTimeSpent;
    }

    // NOTE: not really used because requires analysis of long term data
    public int getProductivity() {
        return controller.getProductivity(this.getName());
    }

    // Finds the user's average productivity
    // NOTE: not really used because requires analysis of long term data
    public int getAvgProductivity() {
        int avgProductivity = 0;
        for (TaskOccurrence occurrence : taskOccurrences) {
            if (occurrence.getProductivity() != -1) {
                avgProductivity += occurrence.getProductivity();
            }
        }
        return (avgProductivity / taskOccurrences.size());
    }
}

package finalProject;

public class Task {
    private String name;
    private boolean isRunning = false;
    TimerLogic aTimer = new TimerLogic();

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Calls startTask() if its not running and endTask() if it is.
     */
    public void changeTaskState() {
        if (!this.isRunning) {
            startTask();
        } else {
            endTask();
        }
        this.isRunning = !this.isRunning;
    }

    // The following two methods will be used to call other classes.
    private void startTask() {
        aTimer.logStartTime();
        String beginTaskStatement = "Task " + this.getName() + " has begun.";
        System.out.println(beginTaskStatement);
    }

    private void endTask() {
        aTimer.logEndTime();
        String endTaskStatement = "Task " + this.getName() + " has ended.";
        System.out.println(endTaskStatement);
    }
}

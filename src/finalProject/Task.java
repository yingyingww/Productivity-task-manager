package finalProject;

public class Task {
    private String name;
    private boolean isRunning = false;
    Main m;
    TimerLogic a = new TimerLogic();

    public Task(String name, Main m) {
        this.name = name;
        this.m = m;
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
        //filler code to start...
        String beginTaskStatement = "Task " + this.getName() + " has begun.";
        System.out.println(beginTaskStatement);
        a.logStartTime();
    }

    private void endTask() {
        //filler code to start...
        String endTaskStatement = "Task " + this.getName() + " has ended.";
        System.out.println(endTaskStatement);
        m.productivityCheck();
        a.logEndTime();
    }
}

package finalProject;

public class Task {
    private String name;
    private boolean isRunning = false;
    private Main mainPage;
    private TimerLogic aTimer = new TimerLogic();

    public Task(String name, Main mainPage) {
        this.name = name;
        this.mainPage = mainPage;
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

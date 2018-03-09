package finalProject;

public class Task implements Comparable<Task>{
    private String name;
    private boolean isRunning = false;
    TimerLogic a = new TimerLogic();
    Main m;

    public Task(String name, Main m) {
        this.name = name;
        this.m = m;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) { return true; }
        if (!(obj instanceof Task)) { return false; }
        Task that = (Task) obj;
        return this.getName().equals(that.getName());
    }

    @Override
    public int hashCode(){
        return this.getName().hashCode();
    }

    @Override
    public int compareTo(Task that){
        return this.getName().compareTo(that.getName());
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

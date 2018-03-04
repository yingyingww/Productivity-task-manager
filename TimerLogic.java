// Timer Logic (Model) - Yingying
// Start/stop timer function// Ask for productivity when stop is called
// Store task, start, stop, and productivity
// Find total time

import java.util.TimerTask
import java.util.Timer;

//useful source: https://www.ibm.com/developerworks/library/j-schedule/index.html
public class Timer {
    private final Timer timer = new Timer();

    public void start(){
        //final long start = System.currentTime();
        timer.schedule(new TimerTask() {
        }
}

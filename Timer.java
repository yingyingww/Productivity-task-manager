// Timer Logic (Model) - Yingying
// Start/stop timer function// Ask for productivity when stop is called
// Store task, start, stop, and productivity
// Find total time

//import java.util.TimerTask;
import java.util.Date;
//import java.util.Timer;

public class Timer {
    public Date time;
    public Date startTime;
    public Date endTime;
    public int timeInterval;


    public Timer myTimer = new Timer();
    private int minutes;

    //public TimerTask task = new TimerTask(){
        //public void run(){
            //minutes++;

    //}
//System.currentTimeMillis()
    public void start(){
        startTime = time.getTime();
        System.out.println("Start time is " + startTime);
    }

    public void end(){
        endTime = time.getTime();
        timeInterval = endTime -startTime;
        System.out.println("End time is "+endTime);
        System.out.println("Time interval is  "+ timeInterval);

    }

}



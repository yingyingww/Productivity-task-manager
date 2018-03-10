package finalProject;
/**
 * The TimerLogic java file is a will log the start/end Time of a Task
 *
 */

// controller
// Timer Logic (Model)
// Start/stop timer function// Ask for productivity when stop is called
// Store task, start, stop, and productivity
// Find total time
// still need to work on finding the time interval

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class TimerLogic {
    private Date startTime;
    private Date endTime;
    //private Calendar startCal;


    public Date logStartTime(){
        Calendar startCal = Calendar.getInstance();
        startTime = startCal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("Start Time is: " + sdf.format(startTime) );
        return startTime;
    }

    public Date logEndTime(){
        Calendar endCal = Calendar.getInstance();
        endTime = endCal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("End Time is: " + sdf.format(endTime));
        getTimeInterval();
        return endTime;
    }

    public long getTimeInterval(){
        long intervalInMinutes;
        long intervalInMs = endTime.getTime() - startTime.getTime();
        long intervalInSeconds = TimeUnit.MILLISECONDS.toSeconds(intervalInMs);
        //System.out.println("Time Interval is: " + intervalInSeconds + " seconds");
        if (intervalInSeconds % 60 == 0){
            intervalInMinutes = TimeUnit.MILLISECONDS.toMinutes(intervalInMs);
            System.out.println("Time Interval is: " + intervalInMinutes + " minute(s)");
        }
        else{
            intervalInMinutes = TimeUnit.MILLISECONDS.toMinutes(intervalInMs)+1;
            System.out.println("Time Interval is: " + intervalInMinutes + " minute(s)");
        }
        return intervalInMinutes;
    }

     public static void main(String[] args) {
        TimerLogic a = new TimerLogic();
        a.logStartTime();
        //wait for 3 secs
         try {
             Thread.sleep(3000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         a.logEndTime();
     }
}




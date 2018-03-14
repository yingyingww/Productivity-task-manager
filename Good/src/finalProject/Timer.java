package finalProject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
/**
 * The Timer java file is a controller file that
 * will log the start/end Time of a Task and find the time interval of a task
 *
 */

public class Timer {
    private Date startTime;
    private Date endTime;
    private int duration;


    public void logStartTime(){
        Calendar startCal = Calendar.getInstance();
        startTime = startCal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("Start Time is: " + sdf.format(startTime) );
    }

    public void logEndTime(){
        Calendar endCal = Calendar.getInstance();
        endTime = endCal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("End Time is: " + sdf.format(endTime));
        getTimeInterval();
    }

    public int getTimeInterval(){
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
        return (int) intervalInMinutes;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

     public static void main(String[] args) {
        Timer a = new Timer();
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

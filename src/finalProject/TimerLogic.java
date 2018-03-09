package finalProject;

// controller
// Ask for productivity when stop is called
// Store task, start, stop, and productivity


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
        return endTime;
    }

    public long getTimeInterval(){
        long intervalInMs = endTime.getTime() - startTime.getTime();
        long intervalInMinutes = TimeUnit.MILLISECONDS.toMinutes(intervalInMs);
        long intervalInSeconds = TimeUnit.MILLISECONDS.toSeconds(intervalInMs);
        System.out.println("Time Interval is: " + intervalInSeconds + " seconds");
        System.out.println("Time Interval is: " + intervalInMinutes + " minutes");
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
         a.getTimeInterval();
     }
}




package finalProject;

// controller
// Timer Logic (Model)
// Start/stop timer function// Ask for productivity when stop is called
// Store task, start, stop, and productivity
// Find total time
// still need to work on finding the time interval

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TimerLogic {
    private Date startTime;
    private Date endTime;

    public void start(){
        Calendar startCal = Calendar.getInstance();
        startTime = startCal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("Start Time is: " + sdf.format(startTime) ) ;
    }

    public void end(){
        Calendar endCal = Calendar.getInstance();
        endTime = endCal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("End Time is: " + sdf.format(endTime));
        //timeInterval(startTime, endTime);

    }

    //private void timeInterval(Date startTime, Date endTime) {
        //long duration = end - start;
        //System.out.println("start:"+start + "end:"+end+"Time Interval is: " + duration);

     // }


}

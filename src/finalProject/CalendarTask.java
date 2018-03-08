package finalProject;

import java.util.Date;

public class CalendarTask {
    public long getTotalTime(Date startTime, Date endTime) {
        long totalTime = startTime.getTime() - endTime.getTime();

        return totalTime;
    }
}

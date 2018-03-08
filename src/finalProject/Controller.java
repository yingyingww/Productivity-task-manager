package finalProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class Controller {

    // Changes the format of the time to something acceptable
    public long calendarRectangle(ArrayList taskAttributes) throws ParseException {
        ArrayList rectangleAttributes = new ArrayList();

        String taskName = (String) rectangleAttributes.get(0);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        int startHour = (int) rectangleAttributes.get(1);
        if(rectangleAttributes.get(3) == "PM") {
            startHour += 12;
        }
        String startTimeString = startHour + ":" + rectangleAttributes.get(2).toString() + ":" + "00";

        Date startTime = format.parse(startTimeString);

        int endHour = (int) rectangleAttributes.get(4);
        if(rectangleAttributes.get(6) == "PM") {
            endHour += 12;
        }
        String endTimeString = endHour + ":" + rectangleAttributes.get(5).toString() + ":" + "00";

        Date endTime = format.parse(endTimeString);

        // Call CalendarTask methods
        CalendarTask calTask = new CalendarTask();
        long totalTime = calTask.getTotalTime(startTime, endTime);

        return totalTime;
    }
}

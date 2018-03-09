package finalProject;

import javafx.scene.control.Label;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    Calendar idealCalendar = new Calendar();

    // Changes the format of the time to something acceptable
    public void taskInstanceCreated(ArrayList taskAttributes) throws ParseException {
        ArrayList rectangleAttributes = new ArrayList();

        Label taskName = (Label) taskAttributes.get(0);

        List startArray = Arrays.asList(taskAttributes).subList(1, 3);
        Date startTime = changeIntsToDate(startArray);

        List endArray = Arrays.asList(taskAttributes).subList(4, 6);
        Date endTime = changeIntsToDate(endArray);

        long totalTime = getTotalTime(startTime, endTime);
        List timesArray = Arrays.asList(taskAttributes).subList(1, 6);

        int startPoint = getStartPoint(startArray);
        int heightOfRectangle = getHeightOfRectangle(timesArray);

        idealCalendar.addTaskToCalendar(taskName, startPoint, heightOfRectangle);
    }

    public Date changeIntsToDate(List taskAttributes) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        int hour = (int) taskAttributes.get(0);
        int militaryHour = changeToMilitaryTime((String) taskAttributes.get(2), hour);

        String timeString = militaryHour + ":" + taskAttributes.get(1).toString() + ":" + "00";
        Date time = format.parse(timeString);

        return time;
    }

    public int changeToMilitaryTime(String amPm, int hour) {
        if (amPm == "PM") {
            hour += 12;
        }
        return hour;
    }

    public long getTotalTime(Date startTime, Date endTime) {
        long totalTime = startTime.getTime() - endTime.getTime();

        return totalTime;
    }

    public int getStartPoint(List startArray) {
        int startHour = (int) startArray.get(0);
        int startMinute = (int) startArray.get(1);
        int startTime = 60 * startMinute * startHour;

        Calendar calendar = new Calendar();
        int calHeight = calendar.height;

        int dayLength = 60 * 60 * 24;

        int startPoint = (startTime / dayLength) * calHeight;

        return startPoint;
    }

    public int getHeightOfRectangle(List timesArray) {
        int startHour = changeToMilitaryTime((String) timesArray.get(2), (int) timesArray.get(0));
        int startMinute = (int) timesArray.get(1);
        int endHour = changeToMilitaryTime((String) timesArray.get(5), (int) timesArray.get(3));;
        int endMinute = (int) timesArray.get(4);

        int totalHours = endHour - startHour;
        int totalMinutes = endMinute - startMinute;

        int height = 60 * totalMinutes * totalHours;
        return height;
    }
}

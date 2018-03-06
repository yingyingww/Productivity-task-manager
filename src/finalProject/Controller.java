package finalProject;

import java.sql.Time;
import java.util.ArrayList;

public class Controller {

    // Changes the format of the time to something acceptable
    public ArrayList calendarRectangle(ArrayList taskAttributes) {
        ArrayList rectangleAttributes = new ArrayList();

        String taskName = (String) rectangleAttributes.get(0);
        int startHour = (int) rectangleAttributes.get(1);
        if(rectangleAttributes.get(3) == "PM") {
            startHour += 12;
        }
        Time startTime = new Time(startHour, (int) rectangleAttributes.get(2), 0);

        return rectangleAttributes;
    }
}

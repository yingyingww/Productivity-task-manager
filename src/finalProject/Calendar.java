package finalProject;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Calendar {
    ArrayList<CalendarTaskRectangle> todaysTask;
    private Color[] calendarColors;
    private int numberOfTasks;

    public Calendar() {
        calendarColors = new Color[] {Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.PINK, Color.ALICEBLUE};
        numberOfTasks = 0;
    }

    public void addTaskToCalendar(Label taskName, long totalTime) {
        todaysTask.add(new CalendarTaskRectangle(taskName, totalTime, calendarColors[numberOfTasks]));
        numberOfTasks++;
    }

    public VBox displayCalendar() {
        VBox schedule = new VBox();
        schedule.setAlignment(Pos.TOP_CENTER);
        schedule.setPrefSize(300,800);

        return schedule;
    }
}

package finalProject;

import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Calendar {
    ArrayList<CalendarTaskRectangle> todaysTask;
    private Color[] calendarColors;
    private int numberOfTasks;
    public int height;

    public Calendar() {
        calendarColors = new Color[] {Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.PINK, Color.ALICEBLUE};
        numberOfTasks = 0;
        // TODO: Figure out what it really should be
        height = 2000;
    }

    public void addTaskToCalendar(Label taskName, int rectHeight, int rectStartPoint) {
        todaysTask.add(new CalendarTaskRectangle(taskName, rectHeight, rectStartPoint, calendarColors[numberOfTasks]));
        numberOfTasks++;
    }

    public Pane displayCalendar() {
        Pane schedule = new Pane();
        schedule.setPrefSize(200,2000);

        for(CalendarTaskRectangle task : todaysTask) {
            schedule.getChildren().add(task.setTaskRectangleAsStack());
        }

        return schedule;
    }
}

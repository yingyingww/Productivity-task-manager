package finalProject;

import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Calendar extends Pane {
    ArrayList<CalendarTaskRectangle> todaysTask;
    private Color[] calendarColors;
    private int numberOfTasks;
    public int height;

    public Calendar() {
        calendarColors = new Color[] {Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.PINK, Color.ALICEBLUE};
        numberOfTasks = 0;
        // TODO: Figure out what it really should be
        height = 1440;
        todaysTask = new ArrayList<>();
    }

    public void addTaskToCalendar(Label taskName, int rectHeight, double rectStartPoint) {
        todaysTask.add(new CalendarTaskRectangle(taskName, rectHeight, rectStartPoint, calendarColors[numberOfTasks]));
        numberOfTasks++;
    }

    public Pane displayCalendar() {
        Pane schedule = new Pane();
        schedule.setPrefSize(100,1440);

        if(hasTasks()) {
            for (CalendarTaskRectangle task : todaysTask) {
                schedule.getChildren().add(task.setTaskRectangleAsStack());
                System.out.println("new task");
            }
        }

        return schedule;
    }

    public boolean hasTasks() {
        if(todaysTask == null) {
            return false;
        }
        return true;
    }
}

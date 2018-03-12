package finalProject;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Schedule extends Pane {
    ArrayList<CalendarTaskRectangle> todaysTask;
    //private Color[] calendarColors;
    private int numberOfTasks;
    public int height;

    public Schedule() {
        //calendarColors = new Color[] {Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.PINK, Color.ALICEBLUE, Color.VIOLET};
        numberOfTasks = 0;
        // TODO: Figure out what it really should be
        height = 1500;
        todaysTask = new ArrayList<>();
    }

    public ArrayList<CalendarTaskRectangle> getCalendar() {
        return todaysTask;
    }

    public void addTaskToCalendar(Label taskName, float rectHeight, float rectStartPoint) {
        todaysTask.add(new CalendarTaskRectangle(taskName, rectHeight, rectStartPoint));
        numberOfTasks++;
    }

    public Pane displayCalendar() {
        Pane schedule = new Pane();
        schedule.setPrefSize(200,height);

        if(hasTasks()) {
            for (CalendarTaskRectangle task : todaysTask) {
                schedule.getChildren().add(task.setTaskRectangleAsStack());
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

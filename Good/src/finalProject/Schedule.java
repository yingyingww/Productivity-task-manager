package finalProject;

import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import java.util.ArrayList;

/**
 * The Schedule java file will create a schedule panel to be displayed on GUI pages
 *
 */
public class Schedule extends Pane {
    ArrayList<CalendarTaskRectangle> todaysTask;
    public int height;

    public Schedule() {
        // TODO: Figure out what it really should be
        height = 1500;
        todaysTask = new ArrayList<>();
    }

    public ArrayList<CalendarTaskRectangle> getCalendar() {
        return todaysTask;
    }

    public void addTaskToCalendar(Label taskName, float rectHeight, float rectStartPoint) {
        todaysTask.add(new CalendarTaskRectangle(taskName, rectHeight, rectStartPoint));
    }

    public Pane displayCalendar() {
        Pane schedule = new Pane();
        schedule.setPrefHeight(height);
        if (hasTasks()) {
            for (CalendarTaskRectangle task : todaysTask) {
                schedule.getChildren().add(task.setTaskRectangleAsStack());
            }
        }
        return schedule;
    }

    private boolean hasTasks() {
        if(todaysTask == null) {
            return false;
        }
        return true;
    }
}

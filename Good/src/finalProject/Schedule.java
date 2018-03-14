package finalProject;

import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import java.util.ArrayList;

/**
 * The Schedule java file will create a schedule pane to be displayed on GUI pages
 *
 */
public class Schedule extends Pane {
    ArrayList<CalendarTaskRectangle> todaysTask;
    public int height;

    public Schedule() {
        height = 1500;
        todaysTask = new ArrayList<>();
    }

    public ArrayList<CalendarTaskRectangle> getCalendar() {
        return todaysTask;
    }

    // Adds the raw features of a CalendarTaskRectangle to the array list of the tasks already created
    public void addTaskToCalendar(Label taskName, float rectHeight, float rectStartPoint) {
        todaysTask.add(new CalendarTaskRectangle(taskName, rectHeight, rectStartPoint));
    }

    public Pane displayCalendar() {
        Pane schedule = new Pane();
        schedule.setPrefHeight(height);
        // As long as there are tasks, this iterates through them, displaying them as rectangles
        if (hasTasks()) {
            for (CalendarTaskRectangle task : todaysTask) {
                schedule.getChildren().add(task.setTaskRectangleAsStack());
            }
        }
        return schedule;
    }

    // Checks if any tasks have been added to the calendar yet
    private boolean hasTasks() {
        if(todaysTask == null) {
            return false;
        }
        return true;
    }
}

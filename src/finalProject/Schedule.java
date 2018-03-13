package finalProject;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import java.util.ArrayList;

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
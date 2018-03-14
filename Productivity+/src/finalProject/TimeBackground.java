package finalProject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.layout.RowConstraints;
/**
 * The java file displays the timeBackground for the tasks to be added to the corresponding schedule.
 *
 */
public class TimeBackground {
    ObservableList<String> hours = FXCollections.observableArrayList(
            "12 AM", "1 AM", "2 AM","3 AM","4 AM","5 AM","6 AM","7 AM","8 AM","9 AM","10 AM","11 AM","12 PM",
            "1 PM", "2 PM", "3 PM", "4 PM", "5 PM", "6 PM", "7 PM", "8 PM", "9 PM", "10 PM", "11 PM"
    );
    float dayLength =  (float) 60 *  (float) 24;
    Schedule schedule = new Schedule();
    int calHeight = schedule.height;

    // NOTE: The times are currently slightly off with the start times calculated for the CalendarTaskRectangles
    // and we didn't have time to fix this. It is only noticeable at the end of the day
    public void displayTimes(GridPane schedule) {
        GridPane times = new GridPane();
        times.setPrefSize(75,1500);
        times.getColumnConstraints().add(new ColumnConstraints(75));

        int hour = 0;
        for(String  time : hours) {
            Label timeAsLabel = new Label(time);
            times.add(timeAsLabel, 0,hour);

            // How much space each time cell should take up
            float spacePerHour = ((float) 60 / dayLength) * (float) calHeight;
            times.getRowConstraints().add(new RowConstraints(spacePerHour));

            // Sets the time to the center of the cell
            times.setHalignment(timeAsLabel, HPos.CENTER);
            hour++;
        }
        times.setGridLinesVisible(true);
        // Adds the times to the first row and column of whatever gridpane is passed in
        schedule.add(times, 0, 0);
    }

}

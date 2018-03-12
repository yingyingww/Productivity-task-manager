package finalProject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.layout.RowConstraints;

public class TimeBackground {
    ObservableList<String> hours = FXCollections.observableArrayList(
            "12 AM", "1 AM", "2 AM","3 AM","4 AM","5 AM","6 AM","7 AM","8 AM","9 AM","10 AM","11 AM","12 PM",
            "1 PM", "2 PM", "3 PM", "4 PM", "5 PM", "6 PM", "7 PM", "8 PM", "9 PM", "10 PM", "11 PM"
    );
    float dayLength = 60 * 24;
    Calendar calendar = new Calendar();
    int calHeight = calendar.height;


    public void displayTimes(GridPane schedule) {
        GridPane times = new GridPane();
        times.setPrefSize(75,1500);
        times.getColumnConstraints().add(new ColumnConstraints(75));

        int hour = 0;
        for(String  time : hours) {
            Label timeAsLabel = new Label(time);
            times.add(timeAsLabel, 0,hour);
            //TODO: height should be hour/total cal height
            float spacePerHour = (((60 / dayLength) * (float) calHeight));
            times.getRowConstraints().add(new RowConstraints(spacePerHour));
            times.setHalignment(timeAsLabel, HPos.CENTER);
            hour++;
        }
        times.setGridLinesVisible(true);
        schedule.add(times, 0, 0);
    }

}

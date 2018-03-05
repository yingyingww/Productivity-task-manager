package Main;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;

import java.awt.event.ActionListener;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class SetSchedule extends Application {
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        //VBox createTaskPanel = createTask();
        //HBox createTaskName = taskName();
        //HBox startTimePanel = setStartTime();
        //HBox endTimePanel = setEndTime();
        //HBox createTaskBtn = createTaskButton();
        // how to combine them in V box VBox task = setTask();
        //GridPane schedulesPane = combineSchedules(schedulePane, currSchedulePane);

//        root.setRight(createTaskName);
//        root.setRight(startTimePanel);
//        root.setRight(endTimePanel);
//        root.setBottom(createTaskBtn);
        //root.setRight(createTaskPanel);

        Button createTaskBtn = new Button();
        createTaskBtn.setText("Create Task");
        ObservableList<String> hours = FXCollections.observableArrayList(
                "1", "2","3","4","5","6","7","8","9","10","11","12"
        );
        ObservableList<String> minutes = FXCollections.observableArrayList(
                "00","01", "02","03","04","05","06","07","08","09",
                "10","11","12", "13","14","15","16","17","18","19","20","21",
                "22","23","24","25","26","27","28","29","30","31","32","33",
                "34","35","36", "37","38","39","40","41","42","43","44","45",
                "46","47","48", "49","50","51","52","53","54","55","56",
                "57","58","59"
                );
        ComboBox hoursComboBox = new ComboBox(hours);
        ComboBox minutesComboBox = new ComboBox(minutes);
        //comboBox.setItems(times);

        createTaskBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Created A Task");
            }
        });
        root.setBottom(createTaskBtn);
        root.setRight(hoursComboBox);
        root.setTop(minutesComboBox);

        Scene scene = new Scene(root,700,500);
        primaryStage.setTitle("Set Schedule");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //private VBox createTask() {
        //VBox taskPanel = new VBox();
        //Text taskPanelDirections = new Text("Create A New Task");
        //HBox taskName = createTaskName();
        //HBox startTIme = createStartTime();
    //}

    public static void main(String[] args) {
        launch(args);
    }
}

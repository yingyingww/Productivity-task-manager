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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    ObservableList<String> amPm = FXCollections.observableArrayList(
            "AM","PM"
    );

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        VBox createTaskPanel = createTask();
        //probably create a gridpanel for the setschedule
        root.setRight(createTaskPanel);

        ComboBox hoursComboBox = new ComboBox(hours);
        ComboBox minutesComboBox = new ComboBox(minutes);

        Scene scene = new Scene(root,700,500);
        primaryStage.setTitle("Set Schedule");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private VBox createTask() {
        VBox newTaskPane = new VBox();
        Text taskPanelDirections = new Text("Create A New Task");
        taskPanelDirections.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        newTaskPane.setPadding(new Insets(15, 12, 15, 12));
        newTaskPane.setSpacing(12);
        newTaskPane.setStyle("-fx-background-color: orange;");

        HBox taskName = createTaskName();
        HBox startTime = createStartTime();
        HBox endTime = createEndTime();
        Button createTaskBtn = new Button();
        createTaskBtn.setText("Create Task");
        createTaskBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Created A Task");
            }
        });

        newTaskPane.getChildren().addAll(taskPanelDirections, taskName, startTime, endTime, createTaskBtn);
        return newTaskPane;
    }
    private HBox createTaskName(){
        HBox taskN = new HBox();
        Text taskName = new Text("Task Name: ");

        TextField addTaskName = new TextField();
        addTaskName.setPromptText("Create a New Task");

        EventHandler<ActionEvent> printTaskName = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String userInput = addTaskName.getText();
                System.out.println(userInput);
                //only print when click enter, not printing if click "create a task" button at the end
            }
        };

        addTaskName.setOnAction(printTaskName);


        taskN.getChildren().addAll(taskName, addTaskName);
        return taskN;
    }
    private HBox createStartTime(){
        HBox startT= new HBox();
//        startT.setPadding(new Insets(15, 12, 15, 12));
//        startT.setSpacing(10);
//        startT.setStyle("-fx-background-color: YELLOW;");

        Text startTimeText = new Text("Start Time: ");
        ComboBox startHours = new ComboBox(hours);
        Text text = new Text(" : ");
        ComboBox startMinutes = new ComboBox(minutes);
        ComboBox amPM = new ComboBox(amPm);
        startT.getChildren().addAll(startTimeText,startHours, text, startMinutes, amPM);
        return startT;

    }
    private HBox createEndTime(){
        HBox endT= new HBox();

//        endT.setPadding(new Insets(15, 12, 15, 12));
//        endT.setSpacing(10);
//        endT.setStyle("-fx-background-color: #336699;");

        Text endTimeText = new Text("End Time: ");
        ComboBox endHours = new ComboBox(hours);
        Text text = new Text(" : ");
        ComboBox endMinutes = new ComboBox(minutes);
        ComboBox amPM = new ComboBox(amPm);
        endT.getChildren().addAll(endTimeText,endHours,text, endMinutes, amPM);
        return endT;

    }

    public static void main(String[] args) {
        launch(args);
    }
}

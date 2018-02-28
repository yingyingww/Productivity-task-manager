package finalProject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.*;

public class Main extends Application {
    private static final double SCENE_WIDTH = 700;
    private static final double SCENE_HEIGHT = 500;
    TextArea status = new TextArea("");

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();
        VBox taskPane = addTasks();
        //Node statusPane = addStatus();
        HBox menuPane = setMenu();
        VBox schedulePane = addSchedule();
        VBox currSchedulePane = addCurrentSchedule();
        GridPane scheudlesPane = combineSchedules(schedulePane, currSchedulePane);

        root.setRight(taskPane);
        //root.setCenter(statusPane);
        root.setTop(menuPane);
        root.setCenter(scheudlesPane);

        primaryStage.setTitle("Productivity+");
        primaryStage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));
        primaryStage.show();
    }

    private VBox addTasks() {
        VBox radioButtons = new VBox();
        ToggleGroup tasks = new ToggleGroup();
        radioButtons.setAlignment(Pos.TOP_LEFT);
        radioButtons.setPadding(new Insets(5));
        radioButtons.setSpacing(5);

        Text directions = new Text("Select Current Task");
        directions.setFont(Font.font(14));

        RadioButton sleeping = new RadioButton("Sleeping");
        sleeping.setToggleGroup(tasks);
        sleeping.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (sleeping.isSelected()) {
                    status.setText("User is sleeping");
                }
            }
        });

        RadioButton eating = new RadioButton("Eating");
        eating.setToggleGroup(tasks);
        eating.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (eating.isSelected()) {
                    status.setText("User is eating");
                }
            }
        });

        RadioButton homework = new RadioButton("Homework");
        homework.setToggleGroup(tasks);
        homework.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (homework.isSelected()) {
                    status.setText("User is doing homework");
                }
            }
        });

        RadioButton working = new RadioButton("Working");
        working.setToggleGroup(tasks);
        working.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (working.isSelected()) {
                    status.setText("User is working");
                }
            }
        });

        RadioButton cleaning = new RadioButton("Cleaning");
        cleaning.setToggleGroup(tasks);
        cleaning.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (cleaning.isSelected()) {
                    status.setText("User is cleaning");
                }
            }
        });

        RadioButton newTask = new RadioButton("Add a new task");
        newTask.setToggleGroup(tasks);
        newTask.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (newTask.isSelected()) {
                    status.setText("Enter a new task");
                }
            }
        });

        radioButtons.getChildren().addAll(sleeping, eating, homework, working, cleaning, newTask);
        return radioButtons;
    }

    private Node addStatus() {
        FlowPane pane = new FlowPane();
        pane.setAlignment(Pos.TOP_CENTER);
        status.setText("Selected task will appear hear");
        status.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 14));
        status.setWrapText(true);
        status.setPrefColumnCount(20);
        pane.getChildren().add(status);
        return pane;
    }

    private HBox setMenu() {
        HBox menuPane = new HBox();
        menuPane.setAlignment(Pos.TOP_LEFT);

        MenuBar menuBar = new MenuBar();
        menuBar.setMinWidth(SCENE_HEIGHT);

        Menu mainMenu = new Menu("Menu");

        MenuItem setSchedule = new MenuItem("Set Schedule");
        setSchedule.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                status.setText("Set Schedule");
            }
        });

        MenuItem reportProductivity = new MenuItem("Report Productivity");
        reportProductivity.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                status.setText("Report Productivity");
            }
        });

        MenuItem trends = new MenuItem("Trends");
        trends.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                status.setText("Trends");
            }
        });

        MenuItem productivityTips = new MenuItem("Productivity Tips");
        productivityTips.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                status.setText("Productivity Tips");
            }
        });

        mainMenu.getItems().addAll(setSchedule, reportProductivity, trends, productivityTips);

        Menu exitMenu = new Menu("Exit");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        exitMenu.getItems().add(exit);

        menuBar.getMenus().addAll(mainMenu, exitMenu);
        menuPane.getChildren().add(menuBar);

        return menuPane;
    }

    private VBox addSchedule() {
        VBox schedule = new VBox();
        schedule.setAlignment(Pos.TOP_CENTER);

        Rectangle task1 = new Rectangle (100, 100, 100, 50);
        task1.setFill(Color.BLUE);

        Rectangle task2 = new Rectangle (100, 200, 100, 50);
        task2.setFill(Color.RED);

        Rectangle task3 = new Rectangle (100, 300, 100, 50);
        task3.setFill(Color.GREEN);

        Rectangle task4 = new Rectangle (100, 400, 100, 50);
        task4.setFill(Color.PURPLE);

        schedule.getChildren().addAll(task1, task2, task3, task4);
        return schedule;
    }

    private VBox addCurrentSchedule() {
        VBox currSchedule = new VBox();
        currSchedule.setAlignment(Pos.TOP_CENTER);

        Rectangle task1 = new Rectangle (200, 100, 100, 50);
        task1.setFill(Color.YELLOW);

        Rectangle task2 = new Rectangle (200, 200, 100, 50);
        task2.setFill(Color.SILVER);

        Rectangle task3 = new Rectangle (200, 300, 100, 50);
        task3.setFill(Color.BLACK);

        Rectangle task4 = new Rectangle (200, 400, 100, 50);
        task4.setFill(Color.VIOLET);

        currSchedule.getChildren().addAll(task1, task2, task3, task4);
        return currSchedule;
    }

    private GridPane combineSchedules(VBox idealSchedule, VBox currSchedule) {
        GridPane schedule = new GridPane();
        schedule.setAlignment(Pos.TOP_CENTER);
        schedule.add(idealSchedule, 1, 1);
        schedule.add(currSchedule,2, 1);
        return schedule;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

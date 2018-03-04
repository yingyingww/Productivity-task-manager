package finalProject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Main extends Application {
    private static final double SCENE_WIDTH = 700;
    private static final double SCENE_HEIGHT = 500;
    RadioButton[] taskHolder = new RadioButton[10];
    TextArea status = new TextArea("");

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        VBox taskPanel = createTaskPanel();
        HBox menuPane = setMenu();
        VBox schedulePane = addSchedule();
        VBox currSchedulePane = addCurrentSchedule();
        GridPane schedulesPane = combineSchedules(schedulePane, currSchedulePane);

        root.setRight(taskPanel);
        root.setTop(menuPane);
        root.setCenter(schedulesPane);

        primaryStage.setTitle("Productivity+");
        primaryStage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));
        primaryStage.show();
    }

    private VBox createTaskPanel() {
        VBox taskPanel = new VBox();

        Text taskPanelDirections = new Text("Select Current Task");

        HBox addTaskField = createAddTaskField();

        VBox tasks = new VBox();
//        tasks.setAlignment(Pos.TOP_LEFT);
//        tasks.setPadding(new Insets(5));
//        tasks.setSpacing(5);

        ToggleGroup taskGroup = new ToggleGroup();
        for (int i=0; i<10; i++) {
            RadioButton task = new RadioButton(String.valueOf(i));
            task.setVisible(false);
            taskHolder[i] = task;
            task.setToggleGroup(taskGroup);
            tasks.getChildren().add(task);
        }

        taskPanel.getChildren().addAll(taskPanelDirections, addTaskField, tasks);
        return taskPanel;
    }

    private HBox createAddTaskField() {
        HBox newTaskPane = new HBox();

        TextField addTaskField = new TextField();
        addTaskField.setPromptText("Create a New Task");
        Button addTaskButton = new Button("Add Task");

        EventHandler<ActionEvent> printTaskName = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String userInput = addTaskField.getText();
                System.out.println(userInput);
                //really bad/inefficient code, but it works to give us an idea
                for (int i=0; i<10; i++) {
                    if (!taskHolder[i].isVisible()) {
                        taskHolder[i].setText(userInput);
                        taskHolder[i].setVisible(true);
                        break;
                    }
                }
            }
        };

        addTaskField.setOnAction(printTaskName);
        addTaskButton.setOnAction(printTaskName);

        newTaskPane.getChildren().addAll(addTaskField, addTaskButton);
        return newTaskPane;
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
                productivityTips();
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

    private void productivityTips() {
        Alert tips = new Alert(AlertType.NONE);
        tips.setTitle("Productivity Tips");
        tips.setHeaderText(null);
        String tip1 = "You haven't been sleeping much. Try getting 8 hours, and hopefully that will boost your mood.";
        String tip2 = "Try breaking up the time you spend doing homework into smaller chunks.";
        String allTips = tip1 + "\n\n" + tip2;
        tips.setContentText(allTips);

        tips.showAndWait();
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

    /**
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
    */

    public static void main(String[] args) {
        launch(args);
    }
}
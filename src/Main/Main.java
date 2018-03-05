package Main;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.net.URL;

public class Main extends Application {
    ToggleButton[] taskHolder = new ToggleButton[10];
    TextArea status = new TextArea("");

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        VBox taskPanel = createTaskPanel();
        taskPanel.setStyle("-fx-background-color: lightblue;");
        HBox menuPane = setMenu();
        VBox schedulePane = addSchedule();
        VBox currSchedulePane = addCurrentSchedule();
        GridPane schedulesPane = combineSchedules(schedulePane, currSchedulePane);
        schedulesPane.setStyle("-fx-background-color: lightcoral;");

        root.setRight(taskPanel);
        root.setTop(menuPane);
        root.setCenter(schedulesPane);

        primaryStage.setTitle("Productivity+");
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);

        URL url = this.getClass().getResource("/Main/Main.css");
        if (url == null) {
            System.out.println("CSS Not Found. Exiting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);

        primaryStage.show();
    }

    private VBox createTaskPanel() {
        VBox taskPanel = new VBox();

        Label taskPanelDirections = new Label("Select Current Task");

        HBox addTaskField = createAddTaskField();

        VBox tasks = new VBox();

        ToggleGroup taskGroup = new ToggleGroup();
        for (int i=0; i<10; i++) {
            ToggleButton task = new ToggleButton(String.valueOf(i));
            task.setVisible(false);
            taskHolder[i] = task;
            task.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    TimerLogic tml = new TimerLogic();
                    if (task.isSelected()){
                        tml.start();
                    }
                    if (!task.isSelected()){
                        tml.end();
                    }
                    System.out.println(task.isSelected());
                }
            });
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
        menuBar.setMinWidth(700);

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
                productivityCheck();
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

    public static void productivityTips() {
        Alert tips = new Alert(AlertType.NONE);
        tips.setTitle("Productivity Tips");
        tips.setHeaderText(null);
        String tip1 = "You haven't been sleeping much. Try getting 8 hours, and hopefully that will boost your mood.";
        String tip2 = "Try breaking up the time you spend doing homework into smaller chunks.";
        String allTips = tip1 + "\n\n" + tip2;
        tips.setContentText(allTips);

        tips.showAndWait();
    }

    private void productivityCheck() {
        Alert check = new Alert(AlertType.NONE);
        check.setTitle("Productivity Check");
        check.setHeaderText("How Productive did you feel during the last activity?");

        Slider productivity = new Slider(0, 10, 5);
        Label level = new Label("-");
        Button notRelevant = new Button("Not Relevant");
        notRelevant.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                check.close();
            }
        });

        GridPane sliderLabel = new GridPane();

        productivity.setBlockIncrement(1);
        productivity.setShowTickLabels(true);
        productivity.setShowTickMarks(true);
        productivity.setSnapToTicks(true);

        productivity.valueProperty().addListener((obs, oldval, newval) ->
        productivity.setValue(newval.intValue()));

        productivity.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                level.setText("Productivity level: " + newValue);
            }
        });

        sliderLabel.add(productivity, 0, 0);
        sliderLabel.add(level, 0, 1);
        sliderLabel.add(notRelevant, 0,2);
        check.getDialogPane().setContent(sliderLabel);

        check.showAndWait();
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

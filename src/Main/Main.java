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
import javafx.geometry.Insets;

public class Main extends Application {
    TextArea status = new TextArea("");
    TaskButtonHolder taskButtonHolder = new TaskButtonHolder();
    BorderPane root = new BorderPane();

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox taskPanel = addTaskPanel();
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

    /**
     * @return the task panel on the right side of the main page
     */
    private VBox addTaskPanel() {
        VBox taskPanel = new VBox();

        //I want to move these to the CSS if possible
        taskPanel.setPadding(new Insets(10, 10, 10, 10));
        taskPanel.setStyle("-fx-background-color: lightblue;");

        Label directions = new Label("Select Current Task");

        ToggleGroup taskButtonGroup = new ToggleGroup();
        VBox taskButtons = new VBox();
        for (int i=0; i<taskButtonHolder.size(); i++) {
            taskButtons.getChildren().add(taskButtonHolder.get(i));
            taskButtonHolder.get(i).setToggleGroup(taskButtonGroup);
        }

        HBox taskCreator = addTaskCreator();

        taskPanel.getChildren().addAll(directions, taskButtons, taskCreator);
        return taskPanel;
    }
    
    /**
     * updates the task panel after a new button has been added
     */
    private void updateTaskPanel() {
        VBox taskPanel = addTaskPanel();
        root.setRight(taskPanel);
    }

    /**
     * @return the "task creator" text field that allows you
     * to create a new task button
     */
    private HBox addTaskCreator() {
        HBox taskCreator = new HBox();

        TextField taskCreatorField = new TextField();
        taskCreatorField.setPromptText("Add a New Task");
        Button taskCreatorButton = new Button("Add Task");

        EventHandler<ActionEvent> addTask = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String name = taskCreatorField.getText();
                taskButtonHolder.addTaskButton(name);
                updateTaskPanel();
            }
        };

        taskCreatorField.setOnAction(addTask);
        taskCreatorButton.setOnAction(addTask);

        taskCreator.getChildren().addAll(taskCreatorField, taskCreatorButton);
        return taskCreator;
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
        
        MenuItem newUserInfo = new MenuItem("Instructions for New Users");
        newUserInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {newUser();}
        });

        mainMenu.getItems().addAll(setSchedule, reportProductivity, trends, productivityTips, newUserInfo);

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
        Alert tips = new Alert(AlertType.INFORMATION);
        tips.setTitle("Productivity Tips");
        tips.setHeaderText(null);
        String tip1 = "You haven't been sleeping much. Try getting 8 hours, and hopefully that will boost your mood.";
        String tip2 = "Try breaking up the time you spend doing homework into smaller chunks.";
        String allTips = tip1 + "\n\n" + tip2;
        tips.setContentText(allTips);

        tips.showAndWait();
    }

    public static void productivityCheck() {
        Alert check = new Alert(AlertType.CONFIRMATION);
        check.setTitle("Productivity Check");
        check.setHeaderText("How Productive did you feel during the last activity?");

        Slider productivity = new Slider(0, 10, 5);
        Label level = new Label("-");
        /*Button notRelevant = new Button("Not Relevant");
        notRelevant.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                check.close();
            }
        });*/

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
        //sliderLabel.add(notRelevant, 0,2);
        check.getDialogPane().setContent(sliderLabel);

        check.showAndWait();
    }
    
    public static void newUser() {
        Alert info = new Alert(AlertType.INFORMATION);
        info.setTitle("How to Use Productivity+");
        info.setHeaderText("Welcome to Productivity+");
        String info1 = "Thanks for using our app! Our goal is to help you develop your most productive schedule.";
        String info2 = "You can get started by clicking 'Set Schedule' and entering the ideal schedule for your day.";
        String info3 = "Simply type in the name of each activity you would like to do during the day, and enter the";
        String info4 = "start and end times you would prefer. Once you have entered your schedule, head to the main page";
        String info5 = "where you will keep track of your actual schedule. When you begin a task, click that task's button";
        String info6 = "then click it again to end the task. We'll keep track of the rest!";
        String allTips = info1 + "\n\n" + info2 + "\n\n" + info3 + "\n\n" + info4 + "\n\n" + info5 + "\n\n" + info6;
        info.setContentText(allTips);

        info.showAndWait();
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

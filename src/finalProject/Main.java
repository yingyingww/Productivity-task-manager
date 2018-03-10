package finalProject;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javafx.geometry.Insets;

public class Main extends Application {
    TextArea status = new TextArea("");
    private List<ToggleButton> taskButtons = new LinkedList<>();
    VBox taskPanel = new VBox();
    Controller2 c = new Controller2(this);
    VBox taskButtonsView = new VBox();

    public String fart() {
        return "fart";
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        createTaskPanel();
        HBox menuPane = setMenu();
        VBox schedulePane = addSchedule();
        VBox currSchedulePane = addCurrentSchedule();
        GridPane schedulesPane = combineSchedules(schedulePane, currSchedulePane);
        schedulesPane.setStyle("-fx-background-color: #922b21;");

        root.setRight(taskPanel);
        root.setTop(menuPane);
        root.setCenter(schedulesPane);

        primaryStage.setTitle("Productivity+");
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);

        // Add css features
        URL url = this.getClass().getResource("/finalProject/Main.css");
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
    private void createTaskPanel() {
        taskPanel.getChildren().clear();
        //VBox taskPanel = new VBox();

        //I want to move these to the CSS if possible
        taskPanel.setPadding(new Insets(10, 10, 10, 10));
        taskPanel.setStyle("-fx-background-color: #1f618d;");

        Label directions = new Label("Select Current Task");

        HBox taskCreator = addTaskCreator();

        taskPanel.getChildren().addAll(directions, taskButtonsView, taskCreator);
    }

    private void updateTaskButtons() {
        taskButtonsView = new VBox();
        ToggleGroup taskButtonGroup = new ToggleGroup();
        for (int i=0; i<this.taskButtons.size(); i++) {
            taskButtonsView.getChildren().add(this.taskButtons.get(i));
            this.taskButtons.get(i).setToggleGroup(taskButtonGroup);
        }
        createTaskPanel();
    }

    public void addTaskButton(String name) {
        ToggleButton t = new ToggleButton(name);
        t.setOnAction(event -> c.taskClicked(name));
        taskButtons.add(t);
        updateTaskButtons();
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
                c.addTask(name);
            }
        };

        taskCreatorField.setOnAction(addTask);
        taskCreatorButton.setOnAction(addTask);

        taskCreator.getChildren().addAll(taskCreatorField, taskCreatorButton);
        return taskCreator;
    }

    // Create two menus. One main menu for navigation and popups
    // The other for exiting.
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
                SetSchedule scheduleSetter = new SetSchedule();
                scheduleSetter.start(new Stage());
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

        mainMenu.getItems().addAll(setSchedule, trends, productivityTips, newUserInfo);

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

    // Provide the user with tips on being more production
    // Would eventually make sense with what they've logged
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

    // Asks the user how productive they felt after a given task
   public int productivityCheck(String name) {


        int productivityValue = -1;

        ButtonType irrelevant = new ButtonType("Not Relevant", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType submit = new ButtonType("Submit", ButtonBar.ButtonData.OK_DONE);

        Alert check = new Alert(AlertType.CONFIRMATION, "", irrelevant, submit);
        final Button submitButton = (Button) check.getDialogPane().lookupButton(ButtonType.CANCEL);
        check.setTitle("Productivity Check");
        check.setHeaderText("How Productive did you feel during the '" + name + "' activity?");

        Slider productivity = new Slider(0, 10, 5);
        Label level = new Label("-");

        GridPane sliderLabel = new GridPane();

        productivity.setBlockIncrement(1);
        productivity.setShowTickLabels(true);
        productivity.setShowTickMarks(true);
        productivity.setSnapToTicks(true);

        productivity.valueProperty().addListener((obs, oldval, newval) ->
        productivity.setValue(newval.intValue()));

        /*EventHandler<ActionEvent> updateValue = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int productivityValue = (int)productivity.getValue();
            }
        };
        submitButton.setOnAction(updateValue);*/


        productivity.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                level.setText("Productivity level: " + newValue);
                productivityValue = (int)productivity.getValue(); //Stopgap to inelegantly deal with the error caused by eventhandler
          //so the app isn't thrown off while I figure out a better solution
            }
        });

        sliderLabel.add(productivity, 0, 0);
        sliderLabel.add(level, 0, 1);
        check.getDialogPane().setContent(sliderLabel);

        check.showAndWait();
        return productivityValue;
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

    public void errorEnteringTasks(String error) {
        Alert info = new Alert(AlertType.ERROR);
        info.setTitle("Error!");
        info.setHeaderText("Error: " + error);
        info.showAndWait();
    }

    // Currently sets up a dummy schedule
    private VBox addSchedule() {
        VBox schedule = new VBox();
        schedule.setAlignment(Pos.TOP_CENTER);
        schedule.setPrefSize(100,400);

//        Label taskName1 = new Label("Sleeping");
//        CalendarTaskRectangle task1 = new CalendarTaskRectangle(taskName1, 500, Color.YELLOW);
//        StackPane task1Pane = task1.setTaskRectangleAsStack();
//
//        Label taskName2 = new Label("Eating");
//        CalendarTaskRectangle task2 = new CalendarTaskRectangle(taskName2, 50, Color.ALICEBLUE);
//        StackPane task2Pane = task2.setTaskRectangleAsStack();
//
//        Label taskName3 = new Label("Homework");
//        CalendarTaskRectangle task3 = new CalendarTaskRectangle(taskName3, 150, Color.WHITE);
//        StackPane task3Pane = task3.setTaskRectangleAsStack();
//
//        Label taskName4 = new Label("Running");
//        CalendarTaskRectangle task4 = new CalendarTaskRectangle(taskName4, 60, Color.VIOLET);
//        StackPane task4Pane = task4.setTaskRectangleAsStack();
//
//        Label taskName5 = new Label("Sleeping");
//        CalendarTaskRectangle task5 = new CalendarTaskRectangle(taskName5, 500, Color.GREEN);
//        StackPane task5Pane = task5.setTaskRectangleAsStack();
//
//        Label taskName6 = new Label("Eating");
//        CalendarTaskRectangle task6 = new CalendarTaskRectangle(taskName6, 50, Color.CRIMSON);
//        StackPane task6Pane = task6.setTaskRectangleAsStack();
//
//        Label taskName7 = new Label("Homework");
//        CalendarTaskRectangle task7 = new CalendarTaskRectangle(taskName7, 150, Color.AQUA);
//        StackPane task7Pane = task7.setTaskRectangleAsStack();
//
//        Label taskName8 = new Label("Running");
//        CalendarTaskRectangle task8 = new CalendarTaskRectangle(taskName8, 60, Color.CORAL);
//        StackPane task8Pane = task8.setTaskRectangleAsStack();
//
//        schedule.getChildren().addAll(task1Pane, task2Pane, task3Pane, task4Pane, task5Pane, task6Pane, task7Pane, task8Pane);
        schedule.setSpacing(10);
        return schedule;
    }

    // Also sets up a less complex dummy schedule
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

    // Puts the two schedules next two each other to be compared
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

package finalProject;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.*;
import javafx.geometry.Insets;
/**
 * The Main java file is the starting file for Productivity+
 * It will start with setSchedule page and have the options to navigate to the mainPage to
 * log their actual activities.
 *
 */
public class Main extends Application {
    private Controller controller = new Controller(this);
    private BorderPane root = new BorderPane();
    private Schedule idealSchedule = new Schedule();
    private Schedule currentSchedule = new Schedule();
    private TimeBackground timeBackground = new TimeBackground();
    private boolean onMain = true;
    private Form setScheduleForm = new Form(controller);
    private TaskPanel taskPanel = new TaskPanel(controller);

    @Override
    public void start(Stage primaryStage) {
        controller.setForm(setScheduleForm);
        controller.setTaskPanel(taskPanel);

        setSchedule();

        primaryStage.setTitle("Productivity+");
        Scene scene = new Scene(root, 795, 700);
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

    private void setMainPage(){
        onMain = true;
        HBox menuPane = setMenu();
        ScrollPane schedules = makeScheduleScroll(combineSchedules(addIdealSchedule(), addCurrentSchedule()));
        root.setRight(taskPanel);
        root.setTop(menuPane);
        root.setCenter(schedules);
    }

    private void setSchedule() {
        onMain = false;
        HBox menuPane = setMenu();
        root.setTop(menuPane);
        root.setCenter(setScheduleForm);
        root.setRight(makeScheduleScroll(addIdealSchedule()));
    }

    public void taskClicked() {
        currentSchedule.getCalendar();
        root.setCenter(makeScheduleScroll(combineSchedules(addIdealSchedule(), addCurrentSchedule())));
    }

    // Create two menus. One main menu for navigation and popups
    // The other for exiting.
    private HBox setMenu() {

        HBox menuPane = new HBox();
        menuPane.setAlignment(Pos.TOP_LEFT);

        MenuBar menuBar = new MenuBar();
        menuBar.setMinWidth(795);

        Menu mainMenu = new Menu("Menu");

        MenuItem mainPage = new MenuItem("Main Page");
        mainPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setMainPage();
            }
        });

        MenuItem setSchedule = new MenuItem("Set Schedule");
        setSchedule.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setSchedule();
            }
        });

        //TODO: What is trends??
        MenuItem topActivities = new MenuItem("Top Activities Chart");
        topActivities.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                chartTopFiveTasks();
            }
        });

        //TODO: this doesn't work either
        MenuItem productivityTips = new MenuItem("Productivity Tips");
        productivityTips.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                productivityTips();
            }
        });

        mainMenu.getItems().addAll(mainPage, setSchedule, productivityTips, topActivities); //TODO what is topActivities?

        Menu exitMenu = new Menu("Exit");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        exitMenu.getItems().add(exit);

        Menu instructionForNewUsers = new Menu("Instruction");
        MenuItem newUserInfo = new MenuItem("Instructions for New Users");
        newUserInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {newUser();}
        });
        instructionForNewUsers.getItems().addAll(newUserInfo);

        menuBar.getMenus().addAll(mainMenu, instructionForNewUsers, exitMenu);
        menuPane.getChildren().add(menuBar);

        return menuPane;
    }

    public void chartTopFiveTasks(){
        Alert showChart = new Alert(AlertType.INFORMATION);
        showChart.setTitle("Top Five Tasks By Time Spent");
        List<Task> topFive = controller.getTopFive();
        if (topFive.size() >= 5) {
            String name1 = topFive.get(0).getName();
            String name2 = topFive.get(1).getName();
            String name3 = topFive.get(2).getName();
            String name4 = topFive.get(3).getName();
            String name5 = topFive.get(4).getName();
            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("Time Spent")));
            xAxis.setLabel("Task Name");

            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Total Time Spent (in min.)");

            BarChart<String, Number> timeBarChart = new BarChart<>(xAxis, yAxis);
            timeBarChart.setTitle("Your Top Five Activities by Time Spent");

            XYChart.Series<String, Number> series1 = new XYChart.Series<>();
            series1.setName(name1);
            series1.getData().add(new XYChart.Data<>("Time Spent", topFive.get(0).getTotalTimeSpent()));

            XYChart.Series<String, Number> series2 = new XYChart.Series<>();
            series2.setName(name2);
            series2.getData().add(new XYChart.Data<>("Time Spent", topFive.get(1).getTotalTimeSpent()));

            XYChart.Series<String, Number> series3 = new XYChart.Series<>();
            series3.setName(name3);
            series3.getData().add(new XYChart.Data<>("Time Spent", topFive.get(2).getTotalTimeSpent()));

            XYChart.Series<String, Number> series4 = new XYChart.Series<>();
            series4.setName(name4);
            series4.getData().add(new XYChart.Data<>("Time Spent", topFive.get(3).getTotalTimeSpent()));

            XYChart.Series<String, Number> series5 = new XYChart.Series<>();
            series5.setName(name5);
            series5.getData().add(new XYChart.Data<>("Time Spent", topFive.get(4).getTotalTimeSpent()));

            timeBarChart.getData().addAll(series1, series2, series3, series4, series5);

            showChart.getDialogPane().setContent(timeBarChart);
            showChart.showAndWait();
        }
        else {
            String notEnoughData = "It looks like you haven't logged enough activities to show this data. Track at least " +
                    "five activities to see a chart about your time usage!";
            showChart.setHeaderText(notEnoughData);
            showChart.showAndWait();
        }

    }

    //TODO: issue with this based on other issues
     //Provide the user with tips on being more productive
    public void productivityTips() {
        Alert tips = new Alert(AlertType.INFORMATION);
        tips.setTitle("Productivity Tips");
        tips.setHeaderText(null);
        String allTips = controller.getTips();
        tips.setContentText(allTips);
        tips.showAndWait();
    }

    //Asks users to rate the productivity of their last activity
    public int askForProductivity(String name) {
        Label info = new Label("Rate your productivity on a scale of 1 to 10, or click 'Ignore' to provide no rating.");

        Slider rating = new Slider(0, 10, 5);
        rating.setShowTickMarks(true);
        rating.setShowTickLabels(true);
        rating.setMajorTickUnit(1);
        rating.setMinorTickCount(0);
        rating.valueProperty().addListener((obs, oldValue, newValue) ->
                rating.setValue(newValue.intValue()));

        VBox content = new VBox();
        content.getChildren().addAll(info, rating);

        ButtonType ignoreButton = new ButtonType("Ignore", ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert productivityAlert = new Alert(AlertType.CONFIRMATION, null, ButtonType.OK, ignoreButton);
        productivityAlert.getDialogPane().setContent(content);
        productivityAlert.setTitle("Productivity Checkup: " + name);
        productivityAlert.setHeaderText("How productive was the activity '" + name + "'?");

        Optional<ButtonType> response = productivityAlert.showAndWait();
        if (response.isPresent() && response.get() == ButtonType.OK) {
            return (int) rating.getValue();
        } else {
            return -1;
        }
    }

    private static void newUser() {
        Alert info = new Alert(AlertType.INFORMATION);
        info.setTitle("How to Use Productivity+");
        info.setHeaderText("Welcome to Productivity+");
        String info1 = "Thanks for using our app! Our goal is to help you develop your most productive \n" +
                "schedule of the day.";
        String info2 = "You can get started by entering your ideal schedule for the day in the Set Schedule Page.";
        String info3 = "Simply type in the name of each activity you would like to do during the day, and enter the";
        String info4 = "start and end times you would prefer. Once you have entered your schedule, head to \n" +
                "the main page";
        String info5 = "where you will keep track of your actual schedule. When you begin a task, click that task's button";
        String info6 = "then click it again to end the task. We'll log your time and keep track of the rest!";
        String allTips = info1 + "\n\n" + info2 + "\n\n" + info3 + "\n\n" + info4 + "\n\n" + info5 + "\n\n" + info6;
        info.setContentText(allTips);

        info.showAndWait();
    }

    public void errorPopup(String error) {
        Alert info = new Alert(AlertType.ERROR);
        info.setTitle("Error!");
        info.setHeaderText("Error: " + error);
        info.showAndWait();
    }

    public void updateIdealSchedulePane(TaskOccurrence occurrence) {
        String name = occurrence.getName();
        Date start = occurrence.getStart();
        Date end = occurrence.getEnd();
        idealSchedule = controller.updateIdealCalendar(name, start, end);
        root.setRight(makeScheduleScroll(addIdealSchedule()));
    }

    public void useSchedule() {
        setMainPage();
        currentSchedule.getCalendar();
        root.setCenter(makeScheduleScroll(combineSchedules(addIdealSchedule(), addCurrentSchedule())));
    }

    public Schedule getIdealSchedule() {
        return idealSchedule;
    }

    public Schedule getCurrentSchedule() {
        return currentSchedule;
    }

    private Pane addIdealSchedule() {
        Pane idealSchedulePane = idealSchedule.displayCalendar();
        idealSchedulePane.setStyle("-fx-background-color: #63e1ff");
        return idealSchedulePane;
    }

    private Pane addCurrentSchedule() {
        Pane currentSchedulePane = currentSchedule.displayCalendar();
        currentSchedulePane.setStyle("-fx-background-color: #dc82ff");
        return currentSchedulePane;
    }

    private GridPane combineSchedules(Pane idealSchedule, Pane currSchedule) {
        GridPane schedule = new GridPane();
        schedule.setAlignment(Pos.TOP_CENTER);
        schedule.add(idealSchedule, 1, 0);
        schedule.add(currSchedule,2, 0);
        timeBackground.displayTimes(schedule);

        return schedule;
    }

    private ScrollPane makeScheduleScroll(Pane schedule) {
        ScrollPane scrollingSchedule = new ScrollPane();
        if(onMain) {
            scrollingSchedule.setPrefSize(500, 700);
            timeBackground.displayTimes((GridPane) schedule);
            scrollingSchedule.setContent(schedule);
        } else {
            scrollingSchedule.setPrefSize(300, 700);
            GridPane withTimes = new GridPane();
            schedule = idealSchedule.displayCalendar();
            schedule.setStyle("-fx-background-color: #63e1ff");
            withTimes.add(schedule, 1, 0);
            timeBackground.displayTimes(withTimes);
            scrollingSchedule.setContent(withTimes);
        }

        return  scrollingSchedule;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

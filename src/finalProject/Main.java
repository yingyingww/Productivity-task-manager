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

public class Main extends Application {
    private List<ToggleButton> taskButtons = new LinkedList<>();
    VBox taskButtonsView = new VBox();
    BorderPane root = new BorderPane();
    Controller controller = new Controller(this);
    Schedule idealSchedule = new Schedule();
    Schedule currentSchedule = new Schedule();
    TimeBackground timeBackground = new TimeBackground();
    boolean onMain = true;
    Form setScheduleForm = new Form(controller);
    TaskPanel taskPanel = new TaskPanel(controller);
    //VBox taskPanel = new VBox();

    @Override
    public void start(Stage primaryStage) {
        controller.setForm(setScheduleForm);
        controller.setTaskPanel(taskPanel);

        setMainPage();

        primaryStage.setTitle("Productivity+");
        Scene scene = new Scene(root, 1000, 700);
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

    public void setMainPage(){
        onMain = true;
        //createTaskPanel();
        HBox menuPane = setMenu();
        ScrollPane schedules = makeScheduleScroll(combineSchedules(addIdealSchedule(), addCurrentSchedule()));
        //ScrollPane schedulePane = addIdealSchedule();
        //VBox currSchedulePane = addCurrentSchedule();
        Pane filler = new Pane();
        filler.setStyle("-fx-background-color: #9999ff");
        filler.setPrefSize(210, 700);

        root.setRight(taskPanel);
        root.setTop(menuPane);
        root.setLeft(filler);
        root.setCenter(schedules);
    }

    public void setSchedule() {
        onMain = false;
        //VBox taskPanel = createTask();
        //root.setCenter(taskPanel);
        root.setCenter(setScheduleForm);
        root.setRight(makeScheduleScroll(addIdealSchedule()));
    }

    /**
     * @return the task panel on the right side of the main page
     */
    private void createTaskPanel() {
        taskPanel.getChildren().clear();
        //VBox taskPanel = new VBox();

        //I want to move these to the CSS if possible
        taskPanel.setPadding(new Insets(10, 10, 10, 10));

        //set the color...
        taskPanel.setStyle("-fx-background-color: #99ccff");

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

    // TODO: maybe give this method a different name. I'm confused about what it does
    public void addTaskButton(String name) {
        ToggleButton t = new ToggleButton(name);
        // TODO: here is where the thing needs to happen
        // Things aren't happening in the right place right now blah
        t.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.taskClicked(name);
                currentSchedule.getCalendar();
                root.setCenter(makeScheduleScroll(combineSchedules(addIdealSchedule(), addCurrentSchedule())));
            }
        });
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
                String name = taskCreatorField.getText().toUpperCase();
                controller.addTask(name);
            }
        };
        taskCreatorField.setOnAction(addTask);
        taskCreatorButton.setOnAction(addTask);

        taskCreator.getChildren().addAll(taskCreatorField, taskCreatorButton);
        return taskCreator;
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
        menuBar.setMinWidth(1000);

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
//        MenuItem topActivities = new MenuItem("Top Activities Chart");
//        trends.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                //TODO: Error
////                chartTopFiveTasks;
//            }
//        });

        //TODO: this doesn't work either
//        MenuItem productivityTips = new MenuItem("Productivity Tips");
//        productivityTips.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                productivityTips();
//            }
//        });

        mainMenu.getItems().addAll(mainPage, setSchedule/*, topActivities, productivityTips*/); //TODO what is topActivities?

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

    //TODO: having issues with this based on other issues
    //Produces a Bar Chart in an Alert to allow users to compare their time usage 
    //for the five activities they spend the most time on.
//    public void chartTopFiveTasks(){
//        Alert showChart = new Alert(AlertType.INFORMATION);
//        showChart.setTitle("Top Five Tasks By Time Spent");
//        List<Task> topFive = controller.getTopFive();
//        String name1 = topFive.get(0).getName();
//        String name2 = topFive.get(1).getName();
//        String name3 = topFive.get(2).getName();
//        String name4 = topFive.get(3).getName();
//        String name5 = topFive.get(4).getName();
//        CategoryAxis xAxis = new CategoryAxis();
//        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("Time Spent")));
//        xAxis.setLabel("Task Name");
//
//        NumberAxis yAxis = new NumberAxis();
//        yAxis.setLabel("Total Time Spent (in min.)");
//
//        BarChart<String, Number> timeBarChart = new BarChart<>(xAxis, yAxis);
//        timeBarChart.setTitle("Your Top Five Activities by Time Spent");
//
//        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
//        series1.setName(name1);
//        series1.getData().add(new XYChart.Data<>("Time Spent", topFive.get(0).getTotalTimeSpent()));
//
//        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
//        series2.setName(name2);
//        series2.getData().add(new XYChart.Data<>("Time Spent", topFive.get(1).getTotalTimeSpent()));
//
//        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
//        series3.setName(name3);
//        series3.getData().add(new XYChart.Data<>("Time Spent", topFive.get(2).getTotalTimeSpent()));
//
//        XYChart.Series<String, Number> series4 = new XYChart.Series<>();
//        series4.setName(name4);
//        series4.getData().add(new XYChart.Data<>("Time Spent", topFive.get(3).getTotalTimeSpent()));
//
//        XYChart.Series<String, Number> series5 = new XYChart.Series<>();
//        series5.setName(name5);
//        series5.getData().add(new XYChart.Data<>("Time Spent", topFive.get(4).getTotalTimeSpent()));
//
//        timeBarChart.getData().addAll(series1, series2, series3, series4, series5);
//
//        showChart.getDialogPane().setContent(timeBarChart);
//        showChart.showAndWait();
//
//    }

    //TODO: issue with this based on other issues
    // Provide the user with tips on being more productive
//    public void productivityTips() {
//        Alert tips = new Alert(AlertType.INFORMATION);
//        tips.setTitle("Productivity Tips");
//        tips.setHeaderText(null);
//        String allTips = controller.getTips();
//        tips.setContentText(allTips);
//        tips.showAndWait();
//    }

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
        idealSchedulePane.setStyle("-fx-background-color: #9999ff");
        return idealSchedulePane;
//        VBox chosenSchedule = new VBox();
//        chosenSchedule.setStyle("-fx-background-color: #9999ff");
//        ScrollPane schedule = new ScrollPane();
//        Text scrollSchedule = new Text("Ideal Schedule");
//        scrollSchedule.setFont(Font.font("Arial", FontWeight.BOLD, 16));
//        schedule.setPrefSize(200, 1000);
//
//        if(idealSchedule.hasTasks()) {
//            schedule.setContent(idealSchedule.displayCalendar());
//        }
//        chosenSchedule.getChildren().addAll(scrollSchedule, schedule);
//        return chosenSchedule;
    }

    private Pane addCurrentSchedule() {
        Pane currentSchedulePane = currentSchedule.displayCalendar();
        currentSchedulePane.setStyle("-fx-background-color: #9999ff");
        return currentSchedulePane;
    }

    //we probably don't need this any more
    // Puts the two schedules next two each other to be compared
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
            withTimes.add(idealSchedule.displayCalendar(), 1, 0);
            timeBackground.displayTimes(withTimes);
            scrollingSchedule.setContent(withTimes);
        }

        return  scrollingSchedule;
    }

    // there is a lot of stuff in here!
    //    private HBox createWeekday(){
//        HBox wkd = new HBox();
//
//        Button monday = new Button();
//        monday.setText("Monday");
//        monday.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                presetDay = "Monday";
//
//                System.out.println(presetDay);
//            }
//        });
//        Button tuesday = new Button();
//        tuesday.setText("Tuesday");
//        tuesday.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                presetDay = "Tuesday";
//                System.out.println(presetDay);
//            }
//        });
//        Button wednesday = new Button();
//        wednesday.setText("Wednesday");
//        wednesday.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                presetDay = "Wednesday";
//                System.out.println(presetDay);
//
//            }
//        });
//
//        Button thursday = new Button();
//        thursday.setText("Thursday");
//        thursday.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                presetDay ="Thursday";
//                System.out.println(presetDay);
//            }
//        });
//        Button friday = new Button();
//        friday.setText("Friday");
//        friday.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                presetDay = "Friday";
//                System.out.println(presetDay);
//            }
//        });
//        Button saturday = new Button();
//        saturday.setText("Saturday");
//        saturday.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                presetDay = "Saturday";
//                System.out.println(presetDay);
//            }
//        });
//        Button sunday = new Button();
//        sunday.setText("Sunday");
//        sunday.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                presetDay = "Sunday";
//                System.out.println(presetDay);
//            }
//        });
//
//
//        wkd.getChildren().addAll(monday,tuesday, wednesday, thursday, friday, saturday,sunday);
//
//        return wkd;
//    }

    //    private VBox createTask() {
//        VBox newTaskPane = new VBox();
//        Text taskPanelDirections = new Text("Set Your Schedule");
//        taskPanelDirections.setFont(Font.font("Arial", FontWeight.BOLD, 16));
//
//        // We will deal with the preset day if we have time later!
//        //Text chooseDay = new Text("Choose a preset schedule: ");
//
//        newTaskPane.setPadding(new Insets(15, 12, 15, 12));
//        newTaskPane.setSpacing(20);
//        newTaskPane.setStyle("-fx-background-color: #999966");
//
//        ComboBox taskNameOptions = new ComboBox();
//        updateTaskNames(taskNameOptions);
//        taskNameOptions.setPromptText("Create a New Task");
//        taskNameOptions.setEditable(true);
//
//        HBox startTime= new HBox();
//        Text startTimeText = new Text("Start Time: ");
//        ComboBox startHours = new ComboBox(hours);
//        Text startColon = new Text(" : ");
//        ComboBox startMinutes = new ComboBox(minutes);
//        ComboBox startAmPM = new ComboBox(amPm);
//        startTime.getChildren().addAll(startTimeText, startHours, startColon, startMinutes, startAmPM);
//
//        HBox endTime= new HBox();
//        Text endTimeText = new Text("End Time: ");
//        ComboBox endHours = new ComboBox(hours);
//        Text endColon = new Text(" : ");
//        ComboBox endMinutes = new ComboBox(minutes);
//        ComboBox endAmPM = new ComboBox(amPm);
//        endTime.getChildren().addAll(endTimeText, endHours, endColon, endMinutes, endAmPM);
//
//        Button createTaskButton = new Button();
//        createTaskButton.setText("Create Task");
//
//        // When the create task button is clicked, all of the information
//        // is put into an array and sends it to the controller so the
//        // model can use that info.
//        createTaskButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // Maybe should be in model, but very simple in here
//                newTaskNameCheck((String) taskNameOptions.getValue());
//                Label nameInput = new Label((String) taskNameOptions.getValue());
//
//                Object startHoursInput = startHours.getValue();
//                Object startMinutesInput = startMinutes.getValue();
//                Object startAMPMInput = startAmPM.getValue();
//
//                Object endHoursInput = endHours.getValue();
//                Object endMinutesInput = endMinutes.getValue();
//                Object endAMPMInput = endAmPM.getValue();
//
//                ArrayList taskAttributes = new ArrayList();
//                taskAttributes.addAll(Arrays.asList(nameInput, startHoursInput, startMinutesInput, startAMPMInput, endHoursInput, endMinutesInput, endAMPMInput));
//
//                //TODO: THIS IS ALL NONSENSE AND DOESN'T DO SHIT AND IT MAKES SENSE BUT STILL NEEDS FIXING
//                // TODO: Make own method maybe in controller/model?
//                boolean allFieldsFilled = checkIfAllFieldsFilled(taskAttributes);
//                if(!allFieldsFilled) {
//                    // TODO: make this a popup
//                    System.out.println("Fill out everything");
//                    System.out.println("Input a valid time");
//                }
//
//                boolean validTime = true;
//                if(startAMPMInput.equals("PM") && endAMPMInput.equals("AM")) {
//                    validTime = false;
//                } else if(startAMPMInput.equals(endAMPMInput) && (int) startHoursInput > (int) endHoursInput) {
//                    validTime = false;
//                } else if(startAMPMInput.equals(endAMPMInput) && startHoursInput.equals(endHoursInput) && Integer.valueOf((String) startMinutesInput) > Integer.valueOf((String) endMinutesInput)) {
//                    validTime = false;
//                } else if(startAMPMInput.equals(endAMPMInput) && startHoursInput.equals(endHoursInput) && startMinutesInput.equals(endMinutesInput)) {
//                    validTime = false;
//                }
//
//                if (allFieldsFilled && validTime) {
//                    // TODO: Figure out best way to do this
//                    System.out.println("Task Attributes are: " + taskAttributes);
//                    System.out.println(controller);
//                    idealSchedule = controller.updateIdealCalendar(taskAttributes);
//                    root.setRight(makeScheduleScroll(addIdealSchedule()));
//                    updateTaskNames(taskNameOptions);
//                } else {
//                    // TODO: make this a popup
//                    System.out.println("Fill out everything");
//                    System.out.println("Input a valid time");
//                }
//            }
//        });
//
//        //HBox weekdays = createWeekday();
//
//        Button useSchedule = new Button("Use Schedule");
//        useSchedule.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                setMainPage();
//                currentSchedule.getCalendar();
//                root.setCenter(makeScheduleScroll(combineSchedules(addIdealSchedule(), addCurrentSchedule())));
//            }
//        });
//
//        newTaskPane.getChildren().addAll(taskPanelDirections, taskNameOptions, startTime,
//                endTime, createTaskButton, useSchedule);
//
//        return newTaskPane;
//    }
//
//    // TODO: move to model
//    private boolean checkIfAllFieldsFilled(ArrayList taskAttributes) {
//        for(Object attribute : taskAttributes) {
//            if (attribute == null || attribute.equals("")) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    //TODO: this really shouldn't been here maybe in model or controller
//    private void newTaskNameCheck(String taskName) {
//        if(!taskNames.contains(taskName)) {
//            taskNames.add(taskName);
//        }
//    }
//
//    private void updateTaskNames(ComboBox taskNameOptions) {
//        taskNameOptions.getItems().clear();
//        taskNameOptions.getItems().addAll(taskNames);
//    }

    public static void main(String[] args) {
        launch(args);
    }
}

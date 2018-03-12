//package finalProject;
//
//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.GridPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.control.Button;
//import javafx.scene.layout.*;
//
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//
//public class SetSchedule extends Application {
//    ObservableList<Integer> hours = FXCollections.observableArrayList(
//            1, 2,3,4,5,6,7,8,9,10,11,12
//    );
//    ObservableList<String> weekDays = FXCollections.observableArrayList(
//            "Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"
//    );
//    ObservableList<String> minutes = FXCollections.observableArrayList(
//            "00","01", "02","03","04","05","06","07","08","09",
//            "10","11","12", "13","14","15","16","17","18","19","20","21",
//            "22","23","24","25","26","27","28","29","30","31","32","33",
//            "34","35","36", "37","38","39","40","41","42","43","44","45",
//            "46","47","48", "49","50","51","52","53","54","55","56",
//            "57","58","59"
//    );
//    ObservableList<String> amPm = FXCollections.observableArrayList(
//            "AM","PM"
//    );
//    String presetDay;
//    TextArea status = new TextArea("");
//    Controller controller = new Controller(this);
//    Schedule idealSchedule = new Schedule();
//    BorderPane root = new BorderPane();
//
//    @Override
//    public void start(Stage primaryStage) {
//        VBox createTaskPanel = createTask();
//        root.setRight(createTaskPanel);
//        ScrollPane schedulePane = addSchedule();
//
//        //GridPane schedulesPane = combineSchedules(schedulePane);
//        schedulePane.setStyle("-fx-background-color:  #922b21;");
//        root.setCenter(schedulePane);
//        HBox menuPane = setMenu();
//        root.setTop(menuPane);
//        Scene scene = new Scene(root,700,500);
//        primaryStage.setTitle("Set Schedule");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    // Creates all of the buttons and text fields that take in information
//    // about a given task
//    private VBox createTask() {
//        VBox newTaskPane = new VBox();
//        Text taskPanelDirections = new Text("Create A New Task");
//        taskPanelDirections.setFont(Font.font("Arial", FontWeight.BOLD, 16));
//
//        Text chooseDay = new Text("Choose a preset schedule: ");
//
//        newTaskPane.setPadding(new Insets(15, 12, 15, 12));
//        newTaskPane.setSpacing(20);
//        newTaskPane.setStyle("-fx-background-color: #1f618d;");
//
//        HBox taskNameInput = new HBox();
//        Text taskName = new Text("Task Name: ");
//        TextField addTaskName = new TextField();
//        addTaskName.setPromptText("Create a New Task");
//        taskNameInput.getChildren().addAll(taskName, addTaskName);
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
//        ColorPicker colorPicker = new ColorPicker();
//        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Color: " + colorPicker.getValue());
//            }
//        });
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
//                Label nameInput = new Label(addTaskName.getText());
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
//                // TODO: Make own method maybe in controller?
//                boolean allFieldsFilled = true;
//                boolean validTime = true;
//                for(Object attribute : taskAttributes) {
//                    if(attribute == null || attribute.equals("")) {
//                        allFieldsFilled = false;
//                    } if(startAMPMInput.equals("PM") && endAMPMInput.equals("AM")) {
//                        validTime = false;
//                    } else if(startAMPMInput.equals(endAMPMInput) && (int) startHoursInput > (int) endHoursInput) {
//                        validTime = false;
//                    } else if(startAMPMInput.equals(endAMPMInput) && startHoursInput.equals(endHoursInput) && Integer.valueOf((String) startMinutesInput) > Integer.valueOf((String) endMinutesInput)) {
//                        validTime = false;
//                    } else if(startAMPMInput.equals(endAMPMInput) && startHoursInput.equals(endHoursInput) && startMinutesInput.equals(endMinutesInput)) {
//                        validTime = false;
//                    }
//                }
//
//                if (allFieldsFilled && validTime) {
//                    // TODO: Figure out best way to do this
//                    System.out.println("Task Attributes are: " + taskAttributes);
//                    idealSchedule = controller.updateIdealCalendar(taskAttributes);
//                    root.setCenter(addSchedule());
//                } else {
//                    // TODO: make this a popup
//                    System.out.println("Fill out everything");
//                    System.out.println("Input a valid time");
//                }
//            }
//        });
//
//        HBox weekdays = createWeekday();
//
//        newTaskPane.getChildren().addAll(taskPanelDirections, chooseDay, weekdays, taskNameInput, startTime,
//                endTime, /*frequency, */createTaskButton, colorPicker);
//
//        return newTaskPane;
//    }
//
//    /*
//    public void setCalendar(Schedule updatedCalendar) {
//        this.idealSchedule = updatedCalendar;
//    }
//    */
//
//    public Schedule getIdealSchedule() {
//        return idealSchedule;
//    }
//
//    private ScrollPane addSchedule() {
//        ScrollPane schedule = new ScrollPane();
//        schedule.setPrefSize(100, 1000);
//
//        if(idealSchedule.hasTasks()) {
//            schedule.setContent(idealSchedule.displayCalendar());
//        }
//
//        return schedule;
//    }
//
//    // Ideally will allow the user to pick set scheudles
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
//
//
//    private HBox setMenu(){
//        HBox menuPane = new HBox();
//        menuPane.setAlignment(Pos.TOP_LEFT);
//
//        MenuBar menuBar = new MenuBar();
//        menuBar.setMinWidth(700);
//
//        Menu mainMenu = new Menu("Menu");
//
//        MenuItem mainPage = new MenuItem("Main Page");
//        mainPage.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                Main mainWindow = new Main();
//                mainWindow.start(new Stage());
//            }
//        });
//
//        MenuItem trends = new MenuItem("Trends");
//        trends.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                status.setText("Trends");
//            }
//        });
//
//        MenuItem productivityTips = new MenuItem("Productivity Tips");
//        productivityTips.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                Main.productivityTips();
//            }
//        });
//
//        mainMenu.getItems().addAll(mainPage, trends, productivityTips);
//
//        Menu exitMenu = new Menu("Exit");
//        MenuItem exit = new MenuItem("Exit");
//        exit.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.exit(0);
//            }
//        });
//
//        exitMenu.getItems().add(exit);
//
//        menuBar.getMenus().addAll(mainMenu, exitMenu);
//        menuPane.getChildren().add(menuBar);
//
//        return menuPane;
//    }
//
//    /*
//    private GridPane combineSchedules(VBox idealSchedule) {
//        GridPane schedule = new GridPane();
//        schedule.add(new Label("Preset Day:" + presetDay),1,1);
//        // need to work on updating the preset day
//        // currently show as null
//        schedule.setAlignment(Pos.TOP_CENTER);
//        schedule.add(idealSchedule, 1, 2);
//
//        return schedule;
//    }
//    */
//
//    // Not sure if we will use this or not.
//    /*
//    private HBox createFrequency() {
//        HBox freq = new HBox();
//        Button once = new Button();
//        once.setText("One-Time Event");
//        once.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            // Last index will be 0 if one time event and 1 if recurring
//            public void handle(ActionEvent event) {
//                taskAttributes.add(0);
//            }
//        });
//        Button more = new Button();
//        more.setText("Recurring events");
//        more.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                taskAttributes.add(1);
//            }
//        });
//        freq.getChildren().addAll(once, more);
//        return freq;
//
//    }
//    */
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

package Main;
        import javafx.application.Application;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.geometry.Insets;
        import javafx.geometry.Pos;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.layout.GridPane;
        import javafx.scene.paint.Color;
        import javafx.scene.shape.Rectangle;
        import javafx.scene.text.Font;
        import javafx.scene.text.FontWeight;
        import javafx.scene.text.Text;
        import javafx.stage.Stage;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.scene.control.Button;
        import javafx.scene.layout.*;


public class SetSchedule extends Application {
    ObservableList<Integer> hours = FXCollections.observableArrayList(
            1, 2,3,4,5,6,7,8,9,10,11,12
    );
    ObservableList<String> weekDays = FXCollections.observableArrayList(
            "Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"
    );
    ObservableList<String> minutes = FXCollections.observableArrayList(
            "00","01", "02","03","04","05","06","07","08","09",
            "10","11","12", "13","14","15","16","17","18","19","20","21",
            "22","23","24","25","26","27","28","29","30","31","32","33",
            "34","35","36", "37","38","39","40","41","42","43","44","45",
            "46","47","48", "49","50","51","52","53","54","55","56",
            "57","58","59"
    );
//    ObservableList<Integer> minutes = FXCollections.observableArrayList(
//            0,1, 2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
//        21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,
//        46,47,48,49,50,51,52,53,54,55,56,57,58,59
//    );
    ObservableList<String> amPm = FXCollections.observableArrayList(
            "AM","PM"
    );
    String presetDay;
    TextArea status = new TextArea("");

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        VBox createTaskPanel = createTask();
        root.setRight(createTaskPanel);
        VBox schedulePane = addSchedule();

        GridPane schedulesPane = combineSchedules(schedulePane);
        schedulesPane.setStyle("-fx-background-color: lightcoral;");
        root.setCenter(schedulesPane);
        HBox menuPane = setMenu();
        root.setTop(menuPane);
        Scene scene = new Scene(root,700,500);
        primaryStage.setTitle("Set Schedule");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private VBox createTask() {
        VBox newTaskPane = new VBox();
        Text taskPanelDirections = new Text("Create A New Task");
        taskPanelDirections.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Text chooseday = new Text("Choose a preset schedule: ");

        newTaskPane.setPadding(new Insets(15, 12, 15, 12));
        newTaskPane.setSpacing(20);
        newTaskPane.setStyle("-fx-background-color: lightblue;");

        HBox taskName = createTaskName();
        HBox startTime = createStartTime();
        HBox endTime = createEndTime();
        HBox weekdays = createWeekday();
        HBox frequency = createFrequency();
        Button createTaskBtn = new Button();
        createTaskBtn.setText("Create Task");
        createTaskBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Created A Task");
            }
        });

        newTaskPane.getChildren().addAll(taskPanelDirections, chooseday, weekdays, taskName, startTime,
                endTime, frequency, createTaskBtn);
        return newTaskPane;
    }

    private HBox createFrequency() {
        HBox freq = new HBox();
        Button once = new Button();
        once.setText("One-Time Event");
        once.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("This is a one-time Event");
            }
        });
        Button more = new Button();
        more.setText("Recurring events");
        more.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("This is a recurring events");
            }
        });
        freq.getChildren().addAll(once, more);
        return freq;

    }

    private HBox createTaskName(){
        HBox taskN = new HBox();
        Text taskName = new Text("Task Name: ");

        TextField addTaskName = new TextField();
        addTaskName.setPromptText("Create a New Task");
        EventHandler<ActionEvent> printTaskName = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String nameInput = addTaskName.getText();
                System.out.println("Task name is: "+ nameInput);
                //only print when click enter, not printing if click "create a task" button at the end
            }
        };

        addTaskName.setOnAction(printTaskName);


        taskN.getChildren().addAll(taskName, addTaskName);
        return taskN;
    }
    private HBox createStartTime(){
        HBox startT= new HBox();
        Text startTimeText = new Text("Start Time: ");
        ComboBox startHours = new ComboBox(hours);
        startHours.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Object startHoursInput = startHours.getValue();
                System.out.println("Start hour is: "+ startHoursInput);
            }
        });
        Text text = new Text(" : ");
        ComboBox startMinutes = new ComboBox(minutes);
        //One thing I am worried is startMinutesInput read 00, 01, 02 instead of integers 0, 1, 2
        startMinutes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Object startMinutesInput = startMinutes.getValue();
                System.out.println("Start Minute is: "+ startMinutesInput);
            }
        });
        ComboBox amPM = new ComboBox(amPm);
        amPM.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Object startAMPMInput = amPM.getValue();
                System.out.println("Start at:"+startAMPMInput);
            }
        });
        startT.getChildren().addAll(startTimeText,startHours, text, startMinutes, amPM);
        return startT;

    }
    private HBox createEndTime(){
        HBox endT= new HBox();
        Text endTimeText = new Text("End Time: ");
        ComboBox endHours = new ComboBox(hours);
        endHours.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Object endHoursInput = endHours.getValue();
                System.out.println("End hour is: "+ endHoursInput);
            }
        });
        Text text = new Text(" : ");
        ComboBox endMinutes = new ComboBox(minutes);
        // endMinutesInput reads 00, 01, 02 instead of integers 0, 1, 2
        endMinutes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Object endMinutesInput = endMinutes.getValue();
                System.out.println("End Minute is: "+ endMinutesInput);
            }
        });
        ComboBox amPM = new ComboBox(amPm);
        amPM.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Object endAMPMInput = amPM.getValue();
                System.out.println("End at: "+ endAMPMInput);
            }
        });
        endT.getChildren().addAll(endTimeText,endHours,text, endMinutes, amPM);
        return endT;

    }

    private HBox createWeekday(){
        HBox wkd = new HBox();

        Button monday = new Button();
        monday.setText("Monday");
        monday.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                presetDay = "Monday";

                System.out.println(presetDay);
            }
        });
        Button tuesday = new Button();
        tuesday.setText("Tuesday");
        tuesday.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                presetDay = "Tuesday";
                System.out.println(presetDay);
            }
        });
        Button wednesday = new Button();
        wednesday.setText("Wednesday");
        wednesday.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                presetDay = "Wednesday";
                System.out.println(presetDay);

            }
        });

        Button thursday = new Button();
        thursday.setText("Thursday");
        thursday.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                presetDay ="Thursday";
                System.out.println(presetDay);
            }
        });
        Button friday = new Button();
        friday.setText("Friday");
        friday.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                presetDay = "Friday";
                System.out.println(presetDay);
            }
        });
        Button saturday = new Button();
        saturday.setText("Saturday");
        saturday.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                presetDay = "Saturday";
                System.out.println(presetDay);
            }
        });
        Button sunday = new Button();
        sunday.setText("Sunday");
        sunday.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                presetDay = "Sunday";
                System.out.println(presetDay);
            }
        });


        wkd.getChildren().addAll(monday,tuesday, wednesday, thursday, friday, saturday,sunday);

        return wkd;
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

    private HBox setMenu(){
        HBox menuPane = new HBox();
        menuPane.setAlignment(Pos.TOP_LEFT);

        MenuBar menuBar = new MenuBar();
        menuBar.setMinWidth(700);

        Menu mainMenu = new Menu("Menu");

        MenuItem mainPage = new MenuItem("Main Page");
        mainPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                status.setText("Main Page");
            }
        });

        MenuItem reportProductivity = new MenuItem("Report Productivity");
        reportProductivity.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.productivityCheck();
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
                Main.productivityTips();
            }
        });

        mainMenu.getItems().addAll(mainPage, reportProductivity, trends, productivityTips);

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

    private GridPane combineSchedules(VBox idealSchedule) {
        GridPane schedule = new GridPane();
        schedule.add(new Label("Preset Day:" + presetDay),1,1);
        // need to work on updating the preset day
        // currently show as null
        schedule.setAlignment(Pos.TOP_CENTER);
        schedule.add(idealSchedule, 1, 2);

        return schedule;
    }


    public static void main(String[] args) {
        launch(args);
    }
}

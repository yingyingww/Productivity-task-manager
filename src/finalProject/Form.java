package finalProject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Form extends VBox {
    @FXML private ComboBox nameSelector;
    @FXML private ComboBox startHourSelector;
    @FXML private ComboBox startMinuteSelector;
    @FXML private ComboBox startPeriodSelector;
    @FXML private ComboBox endHourSelector;
    @FXML private ComboBox endMinuteSelector;
    @FXML private ComboBox endPeriodSelector;
    private Controller controller;
    private Main main;

    public Form(Controller controller, Main main) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("form.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.controller = controller;
        this.main = main;

        startHourSelector.setItems(createHours());
        startMinuteSelector.setItems(createMinutes());
        startPeriodSelector.setItems(FXCollections.observableArrayList("AM","PM"));

        endHourSelector.setItems(createHours());
        endMinuteSelector.setItems(createMinutes());
        endPeriodSelector.setItems(FXCollections.observableArrayList("AM","PM"));
    }

    private boolean isComplete() {
        boolean nameComplete = !isNull(getName());
        boolean startComplete = !isNull(getStartHour()) && !isNull(getStartMinute()) && !isNull(getStartPeriod());
        boolean endComplete = !isNull(getEndHour()) && !isNull(getEndMinute()) && !isNull(getEndPeriod());
        return nameComplete && startComplete && endComplete;
    }

    //TODO
    public void setNameSelector(List<String> names) {
        ObservableList<String> oNames = FXCollections.observableArrayList(names);
        nameSelector.setItems(oNames);
    }

    public TaskOccurrence getContents() throws FormNotCompleteException, InvertedTimelineException {
        if (!isComplete()) {
            throw new FormNotCompleteException("Please fill out everything.");
        }
        String name = getName();
        Date start = getStart();
        Date end = getEnd();
        if (end.before(start)) {
            throw new InvertedTimelineException("End date occurs before start date.");
        }
        TaskOccurrence contents = new TaskOccurrence(name, start, end);
        return contents;
    }

    private Date getStart() {
        Date start = convertToDate(getStartHour(), getStartMinute(), getStartPeriod());
        return start;
    }

    private Date getEnd() {
        Date end = convertToDate(getEndHour(), getEndMinute(), getEndPeriod());
        return end;
    }

    @FXML
    protected void submitOccurrence() {
        controller.tryAddScheduleOccurrence();
    }

    @FXML
    protected void useSchedule() {
        main.useSchedule();
    }

    /***** THE FOLLOWING 7 METHODS GET DATA FROM THE INPUT FIELDS *****/
    private String getName() {
        return (String) nameSelector.getValue();
    }

    private String getStartHour() {
        return (String) startHourSelector.getValue();
    }

    private String getStartMinute() {
        return (String) startMinuteSelector.getValue();
    }

    private String getStartPeriod() {
        return (String) startPeriodSelector.getValue();
    }

    private String getEndHour() {
        return (String) endHourSelector.getValue();
    }

    private String getEndMinute() {
        return (String) endMinuteSelector.getValue();
    }

    private String getEndPeriod() {
        return (String) endPeriodSelector.getValue();
    }

    /***** STATIC METHOD THAT CLEANS UP TIME INPUTS BEFORE FURTHER USE *****/
    private static Date convertToDate(String hour, String minute, String period) {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DAY_OF_MONTH);
        String fullDay = month + " " + day + " " + hour + ":" + minute + ":00 " + period + " " + year;
        DateFormat formatter = new SimpleDateFormat("MM dd hh:mm:ss a yyyy");
        Date date = null;
        try {
            date = formatter.parse(fullDay);
        } catch (ParseException e) {
            System.out.println("This definitely should not happen: " + e.getMessage());
            System.exit(-1);
        }
        return date;
    }

    /***** THE FOLLOWING 2 STATIC METHODS CREATE WHAT GOES IN TO TIME FIELDS *****/
    private static ObservableList<String> createMinutes() {
        ObservableList<String> minutes = FXCollections.observableArrayList();
        for (int i=0; i<60; i++) {
            String minute = String.format("%02d", i);
            minutes.add(minute);
        }
        return minutes;
    }

    private static ObservableList<String> createHours() {
        ObservableList<String> hours = FXCollections.observableArrayList();
        for (int i = 1; i < 13; i++) {
            String hour = Integer.toString(i);
            hours.add(hour);
        }
        return hours;
    }

    /***** SHORTHAND STATIC METHOD FOR CLEANER CODE *****/
    private static boolean isNull(Object o) {
        return (o == null);
    }

}
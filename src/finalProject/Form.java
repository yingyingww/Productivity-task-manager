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

public class Form extends VBox {
    @FXML private ComboBox name;
    @FXML private ComboBox startHour;
    @FXML private ComboBox startMinute;
    @FXML private ComboBox startPeriod;
    @FXML private ComboBox endHour;
    @FXML private ComboBox endMinute;
    @FXML private ComboBox endPeriod;
    private Main main;

    public Form(Main main) {
        this.main = main;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("form.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        startHour.setItems(createHours());
        startMinute.setItems(createMinutes());
        startPeriod.setItems(FXCollections.observableArrayList("AM","PM"));

        endHour.setItems(createHours());
        endMinute.setItems(createMinutes());
        endPeriod.setItems(FXCollections.observableArrayList("AM","PM"));
    }

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

    private String getName() {
        return (String) name.getValue();
    }

    private String getStartHour() {
        return (String) startHour.getValue();
    }

    private String getStartMinute() {
        return (String) startMinute.getValue();
    }

    private String getStartPeriod() {
        return (String) startPeriod.getValue();
    }

    private String getEndHour() {
        return (String) endHour.getValue();
    }

    private String getEndMinute() {
        return (String) endMinute.getValue();
    }

    private String getEndPeriod() {
        return (String) endPeriod.getValue();
    }

    private static boolean isNull(Object o) {
        return (o == null);
    }

    private boolean isComplete() {
        boolean nameComplete = !isNull(getName());
        boolean startComplete = !isNull(getStartHour()) && !isNull(getStartMinute()) && !isNull(getStartPeriod());
        boolean endComplete = !isNull(getEndHour()) && !isNull(getEndMinute()) && !isNull(getEndPeriod());
        return nameComplete && startComplete && endComplete;
    }

    private static Date convertToDate(String hour, String minute, String period) throws ParseException {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DAY_OF_MONTH);
        System.out.println(hour);
        String time = month + " " + day + " " + hour + ":" + minute + ":00 " + period + " " + year;
        DateFormat formatter = new SimpleDateFormat("MM dd hh:mm:ss a yyyy");
        Date date = formatter.parse(time);
        return date;
    }

    @FXML
    protected void createInstance() {
        if(isComplete()) {
            String name = getName();
            try {
                Date start = convertToDate(getStartHour(), getStartMinute(), getStartPeriod());
                System.out.println(start);
                Date end = convertToDate(getEndHour(), getEndMinute(), getEndPeriod());
                main.updateIdealSchedulePane(name, start, end);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                System.exit(-1);
            }
        }
    }

    @FXML
    protected void useSchedule() {
        main.useSchedule();
    }

}
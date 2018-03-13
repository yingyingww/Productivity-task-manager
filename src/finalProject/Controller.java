package finalProject;

import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.scene.control.Label;

import java.util.ArrayList;

import static finalProject.Model.*;

public class Controller {
    Main main;
    Model model;

    public Controller(Main main) {
        this.main = main;
    }

    public void setModel(Model m) {
        this.model = m;
    }

    public void addTask(String name) {
        try {
            model.addTask(name);
            main.addTaskButton(name);
        } catch (EmptyTaskNameException e) {
            main.errorEnteringTasks(e.getMessage());
        } catch (TaskAlreadyExistsException e) {
            main.errorEnteringTasks(e.getMessage());
        }
    }

    public void taskClicked(String name) {
        Task t = model.getTask(name);
        model.switchTasks(t);
    }

    public int getProductivity(String name) {
        return this.main.askForProductivity(name);
    }

    // Changes the format of the time to something acceptable
    public Schedule updateIdealCalendar(ArrayList taskAttributes) {
        Label taskName = (Label) taskAttributes.get(0);

        float startPoint = getStartPoint(taskAttributes);
        float heightOfRectangle = getHeightOfRectangle(taskAttributes);

        Schedule currentIdealSchedule = main.getIdealSchedule();

        currentIdealSchedule.addTaskToCalendar(taskName, heightOfRectangle, startPoint);

        return currentIdealSchedule;

        // TODO: Andrew wants me to send stuff to model to be stored, but errors.

        //Date startTime = changeIntsToDate(startArray);
        //List endArray = Arrays.asList(taskAttributes).subList(4, 6);
        //Date endTime = changeIntsToDate(endArray);
        //long totalTime = getTotalTime(startTime, endTime);
    }

    public Schedule updateCurrentCalendar(ArrayList taskAttributes) {
        Label taskName = (Label) taskAttributes.get(0);

        float startPoint = getStartPoint(taskAttributes);
        float heightOfRectangle = getHeightOfRectangle(taskAttributes);

        Schedule currentSchedule = main.getCurrentSchedule();

        currentSchedule.addTaskToCalendar(taskName, heightOfRectangle, startPoint);

        return currentSchedule;

        // TODO: Andrew wants me to send stuff to model to be stored, but errors.

    }

    public int changeToMilitaryTime(String amPm, int hour) {
        if (amPm == "PM" && hour < 12) {
            hour += 12;
        } if (amPm == "AM" && hour == 12) {
            hour = 0;
        }
        return hour;
    }

    public float getStartPoint(ArrayList startArray) {
        int startHour = changeToMilitaryTime((String) startArray.get(3), (int) startArray.get(1));
        int startMinute = Integer.valueOf((String) startArray.get(2));
        int startTime = startMinute + (60 * startHour);

        Schedule schedule = new Schedule();
        int calHeight = schedule.height;

        int dayLength = 60 * 24;

        float startPoint = ((float) startTime / (float) dayLength) * (float) calHeight;

        return startPoint;
    }

    public float getHeightOfRectangle(ArrayList timesArray) {
        int startHour = changeToMilitaryTime((String) timesArray.get(3), (int) timesArray.get(1));
        int startMinute = Integer.valueOf((String) timesArray.get(2));
        int endHour = changeToMilitaryTime((String) timesArray.get(6), (int) timesArray.get(4));;
        int endMinute = Integer.valueOf((String) timesArray.get(5));

        int totalHours = endHour - startHour;
        int totalMinutes = endMinute - startMinute;

        Schedule schedule = new Schedule();
        int calHeight = schedule.height;
        float dayLength = (float) (60 * 24);

        float height = (((float) totalMinutes + (60 * (float) totalHours)) / dayLength) * (float) calHeight;
        System.out.println("height: " + height);
        return height;
    }

    /*
    public Date changeIntsToDate(List taskAttributes) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        int hour = (int) taskAttributes.get(0);
        int militaryHour = changeToMilitaryTime((String) taskAttributes.get(2), hour);

        String timeString = militaryHour + ":" + taskAttributes.get(1).toString() + ":" + "00";
        Date time = format.parse(timeString);

        return time;
    }

    public long getTotalTime(Date startTime, Date endTime) {
        long totalTime = startTime.getTime() - endTime.getTime();

        return totalTime;
    }
    */

    public String getTips(){
        Task lowProductivity = Model.findLeastProductive();
        String tip1 = Model.checkProductivityByDuration(lowProductivity);
        Task highProductivity = Model.findMostProductive();
        String tip2 = ("You usually rate your productivity during " + highProductivity.getName() + " activity highly, well done!");
        String tip3 = "Here is some information about the five activities you spend the most time on:" + topFiveToTip();
        String tips = tip1 + tip2 + tip3;
        return (tips);
    }
}

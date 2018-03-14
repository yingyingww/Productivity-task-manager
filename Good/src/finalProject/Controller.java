package finalProject;

import javafx.scene.control.Label;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The is a Controller java file will supply Main with necessary 
 * information to display tasks and negotiate with other java files, such as the model that
 * work on specific functions
 */

public class Controller {
    private Main main;
    private Model model;
    private Form form;
    private TaskPanel taskPanel;

    public Controller(Main main) {
        this.main = main;
        model = new Model(this);
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public void setTaskPanel(TaskPanel taskPanel) {
        this.taskPanel = taskPanel;
    }

    // TODO: can I delete this?
    public void addTask(String name) {
        try {
            model.addTask(name);
        } catch (EmptyTaskNameException | TaskAlreadyExistsException e) {
            main.errorPopup(e.getMessage());
        }
    }

    /*
     * After a task has been added, the drop down menu on set schedule and
     * list of buttons on main need to be updated with this information
     */
    public void noteTaskAdded(String name) {
        taskPanel.addTaskButton(name);
        form.updateNameSelector(name);
    }

    public void useSchedule() {
        main.useSchedule();
    }

    public void taskClicked(String name) {
        Task t = model.getTask(name);
        model.switchTasks(t);
        main.taskClicked();
    }

    public int getProductivity(String name) {
        return this.main.askForProductivity(name);
    }

    // TODO: Make it so these are not so redundant
    /*
     * Takes in all the information needed to make a CalendarTaskRectangle on the ideal calendar
     * and adds the rectangle to the ideal schedule calendar
     */
    public Schedule updateIdealCalendar(String name, Date start, Date end) {
        Label taskName = new Label(name);
        float startPoint = getSchedulePoint(start);
        float heightOfRectangle = getHeightOfRectangle(start, end);

        // Needs the calendar in its current state before it can update it
        Schedule currentIdealSchedule = main.getIdealSchedule();
        currentIdealSchedule.addTaskToCalendar(taskName, heightOfRectangle, startPoint);

        return currentIdealSchedule;
    }

    /*
     * Takes in all the information needed to make a CalendarTaskRectangle on the current calendar
     * and adds the rectangle to the current schedule calendar
     */
    public Schedule updateCurrentCalendar(String name, Date start, Date end) {
        Label taskName = new Label(name);
        float startPoint = getSchedulePoint(start);
        float heightOfRectangle = getHeightOfRectangle(start, end);

        // Needs the calendar in its current state before it can update it
        Schedule currentSchedule = main.getCurrentSchedule();
        currentSchedule.addTaskToCalendar(taskName, heightOfRectangle, startPoint);

        return currentSchedule;
    }

    /*
     * Finds the point on the grid pane where the top left CalendarTaskRectangle
     * should be placed in the entire Calendar
     */
    private float getSchedulePoint(Date date) {
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        int hour = time.get(Calendar.HOUR_OF_DAY);
        int minute = time.get(Calendar.MINUTE);
        // The start time of the task in terms of minutes
        int calTime = 60 * hour + minute;

        Schedule schedule = new Schedule();
        int calHeight = schedule.height;

        // The length of the day in terms of minutes
        int dayLength = 60 * 24;

        // The start point is computed to make sense on the calendar pane
        float startPoint = ((float) calTime / (float) dayLength) * (float) calHeight;
        return startPoint;
    }

    /*
     * Finds how tall the CalendarTaskRectangle should be based on the height of the schedule
     */
    private float getHeightOfRectangle(Date start, Date end) {
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(start);
        int startHour = startTime.get(Calendar.HOUR_OF_DAY);
        int startMinute = startTime.get(Calendar.MINUTE);

        Calendar endTime = Calendar.getInstance();
        endTime.setTime(end);
        int endHour = endTime.get(Calendar.HOUR_OF_DAY);
        int endMinute = endTime.get(Calendar.MINUTE);

        int totalHours = endHour - startHour;
        int totalMinutes = endMinute - startMinute;
        float totalTime = (float) totalMinutes + (60 * (float) totalHours);

        Schedule schedule = new Schedule();
        int calHeight = schedule.height;
        float dayLength = (float) (60 * 24);

        // Finds the height by getting the proportion of the day that is spent doing it and then
        // having it make sense it terms of the calendar
        float height = (totalTime / dayLength) * (float) calHeight;
        System.out.println("height: " + height);
        return height;
    }

    /*
     * Once the user says the want to do something at a time, this tries to send it to the model
     * to be dealt with and add it to the ideal schedule. Won't happen if the form is incorrect or
     * incomplete
     */
    public void tryAddScheduleOccurrence() {
        try {
            TaskOccurrence contents = form.getContents();
            model.addScheduleOccurrence(contents);
            main.updateIdealSchedulePane(contents);
        } catch (FormNotCompleteException | InvertedTimelineException | OccurrenceOverlapException e) {
            main.errorPopup(e.getMessage());
        }
    }

    /*
     * If the user wants to do a task that they've never done before, it is sent to the model to be added
     */
    public void tryAddTask() {
         try {
             String name = taskPanel.getName();
             model.addTask(name);
         } catch (EmptyTaskNameException | TaskAlreadyExistsException e) {
             main.errorPopup(e.getMessage());
         }
    }


    /*
     * Provides the user with productivity tips based on the data collected on their past schedules
     * NOTE: currently isn't able to provide real tips because we did not get to the point of storing data
     */
    public String getTips(){
        Task lowProductivity = Model.findLeastProductive();
        String tip1 = "";
        String tip2 = "";
        if (lowProductivity == null){
             tip1 = "You don't have enough data to find your least productive task";
        }
        else {
             tip1 = Model.checkProductivityByDuration(lowProductivity);
        }
        Task highProductivity = Model.findMostProductive();
        if (highProductivity == null){
            tip2 = "You don't have enough data to find your most productive task";
        }
        else {
            tip2 = Model.checkProductivityByDuration(highProductivity);
        }
        String tip3 = "Here is some information about the activities you spend the most time on:" + "\n" + model.topFiveToTip();
        String tips = tip1 + "\n" + tip2 + "\n" + tip3;
        return (tips);
    }

    // Gets a list containing the five Task objects with the highest total time recorded.
    public List<Task> getTopFive(){
        List<Task> topFive = Model.findTopFiveByTime("real");
        return topFive;
    }
}

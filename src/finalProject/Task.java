package finalProject;

import java.util.*;
/**
 * The Task java file holds information about Task to be
 *
 */
public class Task {
    private String name;
    private boolean isRunning = false;
    private Timer t = new Timer();
    private List<TaskOccurrence> taskOccurrences = new ArrayList<>();
    private Controller controller;
    private int totalTimeSpent;

    public Task(String name, Controller controller) {
        this.name = name;
        this.controller = controller;
    }

    public String getName() {
        return this.name;
    }
    
    public List<TaskOccurrence> getTaskOccurrences() {
        return taskOccurrences;
    }

    /**
     * Calls startTask() if its not running and endTask() if it is.
     */
    public void changeState() {
        if (!this.isRunning) {
            startTask();
        } else {
            endTask();
        }
        this.isRunning = !this.isRunning;
    }

    // The following two methods will be used to call other classes.
    private void startTask() {
        t.logStartTime();

        //TODO: maybe when either of these are called a schedule object is returned
        //return currentSchedule;
        // and when it's called here, it starts the scrolling line
    }

    private void endTask() {
        t.logEndTime();
        Date start = t.getStartTime();
        Date end = t.getEndTime();
        int duration = t.getTimeInterval();
        int productivity = getProductivity();
        totalTimeSpent += duration;
        System.out.println("Task " + name + "\nstarted: " + start + "\nended: " + end + "\nproductivity: " + productivity);
        Schedule currentSchedule = controller.updateCurrentCalendar(name, start, end);
        //return currentSchedule;
        //taskOccurrences.add(new TaskOccurrence(start, end, productivity, duration));

    }

    public int getTotalTimeSpent(){
        return this.totalTimeSpent;
    }

    public int getProductivity() {
        return controller.getProductivity(this.getName());
    }

    public int getAvgProductivity() {
        int avgProductivity = 0;
        for (TaskOccurrence occurrence : taskOccurrences) {
            if (occurrence.getProductivity() != -1) {
                avgProductivity += occurrence.getProductivity();
            }
        }
        return (avgProductivity / taskOccurrences.size());
    }

//TODO delete stuff below
//    //maybe the worst piece of code I have ever written
//    private ArrayList<Object> createTaskAttributes(String name, Date start, Date end) {
//        ArrayList<Object> taskAttributes = new ArrayList<>();
//
//        Label nameInput = new Label(name);
//
//        Calendar startcal = GregorianCalendar.getInstance();
//        startcal.setTime(start);
//        String amPm;
//        int startHour = startcal.get(Calendar.HOUR_OF_DAY);
//        if (startHour > 12) {
//            amPm = "AM";
//        } else {
//            amPm = "PM";
//            startHour = startHour - 12;
//        }
//
//        Object startHoursInput = (Object) startHour;
//        Object startMinutesInput = (Object) Integer.toString(startcal.get(Calendar.MINUTE));
//        Object startAMPMInput = (Object) amPm;
//
//        Calendar endcal = GregorianCalendar.getInstance();
//        startcal.setTime(start);
//        String amPm2;
//        int endHour = startcal.get(Calendar.HOUR_OF_DAY);
//        if (endHour > 12) {
//            amPm2 = "AM";
//        } else {
//            amPm2 = "PM";
//        }
//        endHour = startHour%12;
//
//        Object endHoursInput = (Object) endHour;
//        Object endMinutesInput = (Object) Integer.toString(endcal.get(Calendar.MINUTE));
//        Object endAMPMInput = (Object) amPm2;
//
//        taskAttributes.addAll(Arrays.asList(nameInput, startHoursInput, startMinutesInput, startAMPMInput, endHoursInput, endMinutesInput, endAMPMInput));
//        return taskAttributes;
//    }

}

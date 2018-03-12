package finalProject;

import javafx.scene.control.Label;

import java.util.*;

public class Task {
    private String name;
    private boolean isRunning = false;
    private Timer t = new Timer();
    private List<TaskInstance> TaskInstances = new ArrayList<>();
    private Controller2 controller2;
    private Controller controller;
    private int totalTimeSpent;

    public Task(String name, Controller controller, Controller2 controller2) {
        this.name = name;
        this.controller = controller;
        this.controller2 = controller2;
    }

    public String getName() {
        return this.name;
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
    }

    private void endTask() {
        t.logEndTime();
        Date start = t.getStartTime();
        Date end = t.getEndTime();
        int duration = t.getTimeInterval();
        int productivity = getProductivity();
        totalTimeSpent += duration;
        System.out.println("Task " + name + "\nstarted: " + start + "\nended: " + end + "\nproductivity: " + productivity);
        //TODO: this needs to return the schedule created by this and then update root for it to work
        controller.updateCurrentCalendar(createTaskAttributes(name, start, end));
        //TaskInstances.add(new TaskInstance(start, end, productivity, duration));

    }

    public int getProductivity() {
        return controller2.getProductivity(this.getName());
    }
    
    public int getAvgProductivity() {
        int avgProductivity = 0;
        for (TaskInstance instance : TaskInstances) {
            if (instance.getProductivity() != -1) {
                avgProductivity += instance.getProductivity();
            }
        }
        return (avgProductivity / TaskInstances.size());
    }

    //maybe the worst piece of code I have ever written
    private ArrayList<Object> createTaskAttributes(String name, Date start, Date end) {
        ArrayList<Object> taskAttributes = new ArrayList<>();

        Label nameInput = new Label(name);

        Calendar startcal = GregorianCalendar.getInstance();
        startcal.setTime(start);
        String amPm;
        int startHour = startcal.get(Calendar.HOUR_OF_DAY);
        if (startHour > 12) {
            amPm = "AM";
        } else {
            amPm = "PM";
        }
        startHour = startHour%12;

        Object startHoursInput = (Object) startHour;
        Object startMinutesInput = (Object) Integer.toString(startcal.get(Calendar.MINUTE));
        Object startAMPMInput = (Object) amPm;

        Calendar endcal = GregorianCalendar.getInstance();
        startcal.setTime(start);
        String amPm2;
        int endHour = startcal.get(Calendar.HOUR_OF_DAY);
        if (endHour > 12) {
            amPm2 = "AM";
        } else {
            amPm2 = "PM";
        }
        endHour = startHour%12;

        Object endHoursInput = (Object) endHour;
        Object endMinutesInput = (Object) Integer.toString(endcal.get(Calendar.MINUTE));
        Object endAMPMInput = (Object) amPm2;

        taskAttributes.addAll(Arrays.asList(nameInput, startHoursInput, startMinutesInput, startAMPMInput, endHoursInput, endMinutesInput, endAMPMInput));
        return taskAttributes;
    }

    public int getTotalTimeSpent(){
        return this.totalTimeSpent;
    }


}

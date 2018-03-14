package finalProject;

import java.util.*;

public class Model {
    private static HashMap<String, Task> tasks = new HashMap<>();
    private static Task currentTask;
    private Controller controller;
    private List<TaskInstance> idealInstanceList;

    public Model(Controller controller) {
        this.controller = controller;
    }

    public void addTask(String name) throws EmptyTaskNameException, TaskAlreadyExistsException {
        if (name.isEmpty()) {
            throw new EmptyTaskNameException("Please choose a task name.");
        }
        name = name.toLowerCase();
        if (taskExists(name)){
            throw new TaskAlreadyExistsException("This task already exists.");
        } else {
            addTaskNoErrors(name); // have to change task constructor
        }
    }

    private void addTaskNoErrors(String name) {
        tasks.put(name, new Task(name, controller));
    }

    public void switchTasks(Task newCurrent) {
        newCurrent.changeState();
        if (currentTask == null) {
            currentTask = newCurrent;
        } else if (newCurrent.equals(currentTask)) {
            currentTask = null;
        } else {
            currentTask.changeState();
            currentTask = newCurrent;
        }
    }

    private boolean taskExists(String name) {
        // check to see if the case is the same!!
        return tasks.containsKey(name);
    }

    public Task getTask(String name) {
        name = name.toLowerCase();
        return this.tasks.get(name);
    }

    private void addTaskIdealSchedule(String name) throws EmptyTaskNameException {
        if (name.isEmpty()) {
            throw new EmptyTaskNameException("Please choose a task name.");
        }
        name = name.toLowerCase();
        if (!taskExists(name)){
            addTaskNoErrors(name);
        }
    }

    public void addIdealInstance(String name, Date start, Date end) throws EmptyTaskNameException {
        addTaskIdealSchedule(name);
        if (end.compareTo(start) <= 0) {
            // throw a invalid dates error
        }
        TaskInstance instance = new TaskInstance(start, end, 0, 0);
        if (checkForInstanceOverlap(instance)) {
            // throw overlap error
        } else {
            idealInstanceList.add(instance);
        }

    }

    private boolean checkForInstanceOverlap(TaskInstance instance) {
        for (TaskInstance i : idealInstanceList) {
            if(i.compareTo(instance) == 0) {
                return true;
            }
        }
        return false;
    }


    public static Task findMostProductive() {
        Task curMost = currentTask;
        int tempProductivity = 0;
        for (Task curTask : tasks.values()) {
            if (curTask.getProductivity() > tempProductivity){
                tempProductivity = curTask.getProductivity();
                curMost = curTask;
             }
        }
        return  curMost;
    }

    public static Task findLeastProductive(){
        Task curLeast= currentTask;
        int tempProductivity = 0;
        for (Task curTask : tasks.values()) {
            if (curTask.getAvgProductivity() < tempProductivity){
                tempProductivity = curTask.getProductivity();
                curLeast = curTask;
            }
        }
        return curLeast;
    }

    public static List<Task> findTopFiveByTime(String type){

        List topTasks = new ArrayList<Task>();
        if type.equals("real"){
            for (Task curTask : tasks.values()) {
                topTasks.add(curTask);
            }
            Collections.sort(topTasks);
            topTasks.subList(0,4);
            return topTasks;
        }
        //else if type.equals("ideal"){
          //  List tempList = idealInstanceList;
           
    }
    
    public static String topFiveToTip(){
        List<Task> topFive = findTopFiveByTime();
        String taskInfo = "";
        for (Task t: topFive){
            String tempName = t.getName();
            int tempTime = t.getTotalTimeSpent();
            int tempProductivity = t.getAvgProductivity();
            if (tempProductivity < 0) {
                taskInfo = taskInfo + "Activity name: " + tempName +
                        " Total time spend on activity in hours " + (tempTime / 60) +
                        " No productivity ratings entered";
            }
            else{
                taskInfo = taskInfo + "Activity name: " + tempName +
                        " Total time spend on activity in hours " + (tempTime / 60) +
                        " Average productivity rating " + tempProductivity;
            }
        }
        return taskInfo;
    }


    public static String checkProductivityByDuration(Task testTask) {
        int countOverTwoHours = 0;
        int productivityOverTwoHours = 0;
        int countUnderTwoHours = 0;
        int productivityUnderTwoHours = 0;
        boolean anyOverTwoHours = false;
        boolean anyUnderTwoHours = false;
        String tip = "";
        for (TaskInstance instance: testTask.getTaskInstances()){
            if (instance.getDuration() >= 120) {
                countOverTwoHours++;
                int tempProductivity = instance.getProductivity();
                if (tempProductivity > 0) {
                    productivityOverTwoHours += tempProductivity;
                }
                anyOverTwoHours = true;
            }
            else {
                countUnderTwoHours ++;
                int tempProductivity = instance.getProductivity();
                if (tempProductivity > 0) {
                    productivityUnderTwoHours += tempProductivity;
                }
                anyUnderTwoHours = true;
            }
        }
        if (anyOverTwoHours) {
            productivityOverTwoHours = productivityOverTwoHours / countOverTwoHours;
        }
        else if (testTask.getAvgProductivity() < 5){
            tip = "You only seem to do the " + testTask.getName() + " activity for short periods of time, and it does not have a high" +
                    "productivity rating, try doing " + testTask.getName() +" for longer stretches to be more productive.";
        }
        else {
            tip = "Doing the " + testTask.getName() + " activity for longer stretches seems to be effective, keep it up!";
        }
        if (anyUnderTwoHours){
            productivityUnderTwoHours = productivityUnderTwoHours / countUnderTwoHours;
        }
        else if (testTask.getAvgProductivity() < 5){
            tip = "You only seem to do the " + testTask.getName() + " activity for long periods of time, and it " +
                    "does not have a high productivity rating, try breaking it up to be more productive.";
        }
        else {
            tip = "Doing the " + testTask.getName() + " activity for shorter periods seems to be effective, keep it up!";
        }
        if (anyOverTwoHours && anyUnderTwoHours && productivityOverTwoHours > productivityUnderTwoHours){
            tip = "You are more productive during " + testTask.getName() + "activity when you do it for long stretches." +
                    "When you do this activity for at least 2 hours at a time you rate your productivity an average of " +
                    (productivityOverTwoHours - productivityUnderTwoHours) + "points higher. Try setting time aside for it!";
        }
        if (anyUnderTwoHours && anyOverTwoHours && productivityUnderTwoHours > productivityUnderTwoHours){
            tip = "You are more productive during " + testTask.getName() + "activity when you do it in shorter blocks." +
                    " When you do this activity for less than 2 hours at a time, you rate your productivity an average of "
            + (productivityUnderTwoHours - productivityOverTwoHours) + "points higher. Try breaking your work in to smaller chunks.";
        }
        return (tip);

    }

}

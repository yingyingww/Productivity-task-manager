package finalProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Model {
    private HashMap<String, Task> tasks = new HashMap<>();
    private Task currentTask;
    private Controller controller;
    //private Controller2 controller2;

    public Model(Controller controller/*, Controller2 controller2*/) {
        this.controller = controller;
        //this.controller2 = controller2;
    }

    public void addTask(String name) throws EmptyTaskNameException, taskAlreadyExistsException {
        if (name.isEmpty()) {
            throw new EmptyTaskNameException("Please choose a task name.");
        }
        name = name.toLowerCase();
        if (taskExists(name)){
            throw new taskAlreadyExistsException("This task already exists.");
        } else {
            tasks.put(name, new Task(name, controller/*, controller2*/)); // have to change task constructor
        }
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

    public Task findMostProductive() {
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

    public Task findLeastProductive(){
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

    public List<Task> findTopFiveByTime(){

        List topTasks = new ArrayList<Task>();

        for (Task curTask : tasks.values()) {
            topTasks.add(curTask);
        }
        Collections.sort(topTasks);
        topTasks.subList(0,4);
        return topTasks;
    }

   public String checkProductivityByDuration(Task testTask){
        int countOverTwoHours = 0;
        int productivityOverTwoHours = 0;
        int countUnderTwoHours = 0;
        int productivityUnderTwoHours = 0;
        boolean anyOverTwoHours = false;
        boolean anyUnderTwoHours = false;
        String tip = "";
        for (TaskInstance instance: testTask.getTaskInstances()){
            if (instance.getDuration() >= 120){
                countOverTwoHours ++;
                productivityOverTwoHours += instance.getProductivity();
                anyOverTwoHours = true;
            }
            else {
                countUnderTwoHours ++;
                productivityUnderTwoHours += instance.getProductivity();
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



//}

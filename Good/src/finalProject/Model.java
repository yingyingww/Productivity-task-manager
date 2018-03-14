package finalProject;

import java.util.*;

/**
 * The Model java file holds information about Task and send it to Controller java file
 * This file will also analyze the productivity by finding the most/lest productive tasks
 * and give top five tips
 *
 */

public class Model {
    private static HashMap<String, Task> tasks = new HashMap<>();
    private static Task currentTask;
    private Controller controller;
    private List<TaskOccurrence> scheduleOccurrences = new ArrayList<>();

    public Model(Controller controller) {
        this.controller = controller;
    }

    public void addTask(String name) throws EmptyTaskNameException, TaskAlreadyExistsException {
        if (name.isEmpty()) {
            throw new EmptyTaskNameException("Please choose a task name.");
        }
        name = name.toUpperCase();
        if (taskExists(name)){
            throw new TaskAlreadyExistsException("This task already exists.");
        }
        addTaskNoErrors(name);
    }

    private void addTaskNoErrors(String name) {
        tasks.put(name, new Task(name, controller));
        controller.noteTaskAdded(name);
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
        name = name.toUpperCase();
        return tasks.containsKey(name);
    }

    public Task getTask(String name) {
        name = name.toUpperCase();
        return this.tasks.get(name);
    }

    public void addScheduleOccurrence(TaskOccurrence occurrence) throws OccurrenceOverlapException {
        String name = occurrence.getName().toUpperCase();
        if (!taskExists(name)) {
            addTaskNoErrors(name);
        }
        if (occurrenceOverlapExists(occurrence)) {
            throw new OccurrenceOverlapException("Your schedule already has a task at this time.");
        }
        scheduleOccurrences.add(occurrence);
    }

    private boolean occurrenceOverlapExists(TaskOccurrence occurrence) {
        for (TaskOccurrence i : scheduleOccurrences) {
            if(i.compareTo(occurrence) == 0) {
                return true;
            }
        }
        return false;
    }

    /*
+    Checks the list tasks to find the activity the user has rated with the highest productivity
+    @return the Task object with the highest average productivity
+    */
    public static Task findMostProductive() {
        Task curMost = null;
        if (tasks.size() > 0) {
            int tempProductivity = 0;
            for (Task curTask : tasks.values()) {
                if (curTask.getProductivity() > tempProductivity) {
                    tempProductivity = curTask.getProductivity();
                    curMost = curTask;
                }
            }
        }
        return  curMost;
    }

    /*
+    Checks the list tasks to find the activity the user has rated with the lowest productivity
+    @return the Task object with the lowest average productivity
+    */
    public static Task findLeastProductive(){
        Task curLeast = null;
        if (tasks.size() > 0) {
            int tempProductivity = 0;
            if (tasks.size() > 0) {
                for (Task curTask : tasks.values()) {
                    if (curTask.getAvgProductivity() < tempProductivity) {
                        tempProductivity = curTask.getProductivity();
                        curLeast = curTask;
                    }
                }
            }
        }
        return curLeast;
    }

    public static List<Task> findTopFiveByTime(String type) {

        List topTasks = new ArrayList<Task>();
        if (type.equals("real")){
            for (Task curTask : tasks.values()) {
                topTasks.add(curTask);
            }
            Collections.sort(topTasks);
            if (topTasks.size() >= 5) {
                topTasks.subList(0, 4);
            }
            return topTasks;
        }
        else {
            return topTasks;
        }
    }

    public static String topFiveToTip(){
        List<Task> topFive = findTopFiveByTime("real");
        String taskInfo = "";
        if (topFive.size() == 0){
            taskInfo = "It looks like you don't have any activities logged, try tracking some activities!";
        }
        else{
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
        System.out.println(testTask.getTaskOccurrences());
        if (testTask.getTaskOccurrences().size() > 0) {
            for (TaskOccurrence occurrence : testTask.getTaskOccurrences()) {
                if (occurrence.getDuration() >= 120) {
                    countOverTwoHours++;
                    int tempProductivity = occurrence.getProductivity();
                    if (tempProductivity > 0) {
                        productivityOverTwoHours += tempProductivity;
                    }
                    anyOverTwoHours = true;
                } else {
                    countUnderTwoHours++;
                    int tempProductivity = occurrence.getProductivity();
                    if (tempProductivity > 0) {
                        productivityUnderTwoHours += tempProductivity;
                    }
                    anyUnderTwoHours = true;
                }
            }
            if (anyOverTwoHours) {
                productivityOverTwoHours = productivityOverTwoHours / countOverTwoHours;
            } else if (testTask.getAvgProductivity() < 5) {
                tip = "You only seem to do the " + testTask.getName() + " activity for short periods of time, and it does not have a high" +
                        "productivity rating, try doing " + testTask.getName() + " for longer stretches to be more productive.";
            } else {
                tip = "Doing the " + testTask.getName() + " activity for longer stretches seems to be effective, keep it up!";
            }
            if (anyUnderTwoHours) {
                productivityUnderTwoHours = productivityUnderTwoHours / countUnderTwoHours;
            } else if (testTask.getAvgProductivity() < 5) {
                tip = "You only seem to do the " + testTask.getName() + " activity for long periods of time, and it " +
                        "does not have a high productivity rating, try breaking it up to be more productive.";
            } else {
                tip = "Doing the " + testTask.getName() + " activity for shorter periods seems to be effective, keep it up!";
            }
            if (anyOverTwoHours && anyUnderTwoHours && productivityOverTwoHours > productivityUnderTwoHours) {
                tip = "You are more productive during " + testTask.getName() + "activity when you do it for long stretches." +
                        "When you do this activity for at least 2 hours at a time you rate your productivity an average of " +
                        (productivityOverTwoHours - productivityUnderTwoHours) + "points higher. Try setting time aside for it!";
            }
            if (anyUnderTwoHours && anyOverTwoHours && productivityUnderTwoHours > productivityUnderTwoHours) {
                tip = "You are more productive during " + testTask.getName() + "activity when you do it in shorter blocks." +
                        " When you do this activity for less than 2 hours at a time, you rate your productivity an average of "
                        + (productivityUnderTwoHours - productivityOverTwoHours) + "points higher. Try breaking your work in to smaller chunks.";
            }
            return (tip);
        }
        tip = "You have not logged your activities enough for some forms of analysis. Track your day for data!";
        return (tip);

    }

}

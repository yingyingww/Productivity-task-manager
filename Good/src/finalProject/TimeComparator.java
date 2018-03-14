package finalProject;

import java.util.Comparator;
/**
 * The java class allows task objects to be compared and sorted according to the total time spent
 */

public class TimeComparator implements Comparator<Task>{
    @Override
    public int compare(Task task1, Task task2) {
        int length1 = task1.getTotalTimeSpent();
        int length2 = task2.getTotalTimeSpent();
        return length1 > length2 ? 1:
                length2 > length1 ? -1:
                        0;
    }
}

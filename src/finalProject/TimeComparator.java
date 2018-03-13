package finalProject;

import java.util.Comparator;

public class TimeComparator implements Comparator<Task>{
    @Override
    public int compare(Task o1, Task o2) {
        int length1 = o1.getTotalTimeSpent();
        int length2 = o2.getTotalTimeSpent();
        return length1 > length2 ? 1:
                length2 > length1 ? -1:
                        0;
    }
}

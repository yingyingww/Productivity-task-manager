package finalProject;

import java.util.Date;

public class TaskInstance implements Comparable<TaskInstance> {
    private Date start;
    private Date end;
    private int productivity;
    private int duration;

    public TaskInstance(Date start, Date end, int productivity, int duration) {
        this.start = start;
        this.end = end;
        this.productivity = productivity;
        this.duration = duration;
    }

    @Override
    public int compareTo(TaskInstance differentTaskInstance) {
        if (this.start.compareTo(differentTaskInstance.end) >= 0) {
            return 1;
        } else if(this.end.compareTo(differentTaskInstance.start) <= 0) {
            return 1;
        }
        return 0;
    }

    public Date getStart() {
        return this.start;
    }

    public Date getEnd() {
        return this.end;
    }

    public int getProductivity() {
        return this.productivity;
    }

    public int getDuration() {
        return this.duration;
    }
}

package finalProject;

import java.util.Date;
/**
 * The TaskOccurrence java file records the basic information of a task 
 *
 */
public class TaskOccurrence implements Comparable<TaskOccurrence> {
    private Date start;
    private Date end;
    private int productivity;
    private int duration;
    private String name;

    public TaskOccurrence(Date start, Date end, int productivity, int duration) {
        this.start = start;
        this.end = end;
        this.productivity = productivity;
        this.duration = duration;
    }

    public TaskOccurrence(String name, Date start, Date end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return this.name;
    }

    public Date getStart() {
        return this.start;
    }

    public Date getEnd() {
        return this.end;
    }

    // Used to determine if two task occurrences overlap
    @Override
    public int compareTo(TaskOccurrence differentTaskOccurrence) {
        if (this.start.compareTo(differentTaskOccurrence.end) >= 0) {
            return 1;
        } else if(this.end.compareTo(differentTaskOccurrence.start) <= 0) {
            return 1;
        }
        return 0;
    }

    public int getProductivity() {
        return this.productivity;
    }

    public int getDuration() {
        return this.duration;
    }
}

package finalProject;

import java.util.Date;

public class TaskInstance {
    private Date start;
    private Date end;
    private int productivity;

    public TaskInstance(Date start, Date end, int productivity) {
        this.start = start;
        this.end = end;
        this.productivity = productivity;
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
}

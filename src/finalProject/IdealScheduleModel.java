package finalProject;

import java.util.ArrayList;
import java.util.List;

public class IdealScheduleModel {
    private List<ArrayList> taskInstances = new ArrayList<ArrayList>();

    public void addTaskInstance(ArrayList taskAttributes) {
        taskInstances.add(taskAttributes);
    }

}
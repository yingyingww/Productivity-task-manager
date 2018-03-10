package finalProject;

public class Controller2 {
    Main main;
    Model model;

    public Controller2(Main main) {
        this.main = main;
        this.model = new Model(this);
    }

    public void addTask(String name) {
        try {
            model.addTask(name);
            main.addTaskButton(name);
        } catch (EmptyTaskNameException e) {
            main.errorEnteringTasks(e.getMessage());
        } catch (taskAlreadyExistsException e) {
            main.errorEnteringTasks(e.getMessage());
        }
    }

    public void taskClicked(String name) {
        Task t = model.getTask(name);
        model.switchTasks(t);
    }


    public int getProductivity(String name) {
        return this.main.productivityCheck(name);
    }
}
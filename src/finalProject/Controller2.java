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
            System.out.println(e.getMessage());
            System.exit(-1);
        } catch (taskAlreadyExistsException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
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
package finalProject;

public class Controller2 {
    Main main;
    Model model;

    public Controller2(Main main, Model model) {
        this.main = main;
        this.model = model;
    }

    public void addTask(String name) {
        try {
            model.addTask(name);

        } catch (EmptyTaskNameException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        } catch (taskAlreadyExistsException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    public void taskClick() {

    }

}

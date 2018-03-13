//package finalProject;
//
//public class Controller2 {
//    Main main;
//    Model model;
//    Controller controller;
//
//    public Controller2(Main main, Controller controller) {
//        this.controller = controller;
//        this.main = main;
//        this.model = new Model(controller,this);
//    }
//
//    public void addTask(String name) {
//        try {
//            model.addTask(name);
//            main.addTaskButton(name);
//        } catch (EmptyTaskNameException e) {
//            main.errorEnteringTasks(e.getMessage());
//        } catch (TaskAlreadyExistsException e) {
//            main.errorEnteringTasks(e.getMessage());
//        }
//    }
//
//    // Needs to return arraylist of "task attributes"
//    public void taskClicked(String name) {
//        Task t = model.getTask(name);
//        model.switchTasks(t);
//
//    }
//
//
//    public int getProductivity(String name) {
//        return this.main.askForProductivity(name);
//    }
//}
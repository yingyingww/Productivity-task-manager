package finalProject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import java.io.IOException;

/**
 * The java file creates the buttons for Main GUI Page 
 */

public class TaskPanel extends VBox{
    ToggleGroup toggleGroup = new ToggleGroup();
    @FXML private VBox buttonHolder;
    @FXML private TextField nameCreator;
    private Controller controller;

    public TaskPanel(Controller controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TaskPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.controller = controller;
    }

    // Called when the user wants to add a button/task to their list of tasks for the day
    @FXML
    protected void addButton() {
        controller.tryAddTask();
    }

    public String getName() throws EmptyTaskNameException {
        String name = nameCreator.getText();
        if (name == null || name.isEmpty()) {
            throw new EmptyTaskNameException("Please choose a task name.");
        }
        return name;
    }

    // Adds a new button to the list of buttons on main
    public void addTaskButton(String name) {
        ToggleButton t = new ToggleButton(name);
        t.setOnAction(event -> controller.taskClicked(name));
        buttonHolder.getChildren().add(t);
        t.setToggleGroup(toggleGroup);
    }

}

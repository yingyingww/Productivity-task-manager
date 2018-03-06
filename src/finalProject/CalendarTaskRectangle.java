package finalProject;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CalendarTaskRectangle {
    public Label taskName;
    public int timeSpent;
    public Color color;

    public CalendarTaskRectangle(Label taskName, int timeSpent, Color color) {
        this.taskName = taskName;
        this.timeSpent = timeSpent;
        this.color = color;
    }

    public StackPane setTaskRectangleAsStack() {
        StackPane stack = new StackPane();

        int height = (timeSpent/16) + 10;

        Rectangle taskRectangle = new Rectangle(100, height);
        taskRectangle.setFill(color);

        taskName.setTextFill(Color.WHITE);

        stack.getChildren().addAll(taskRectangle, taskName);

        return stack;
    }
}

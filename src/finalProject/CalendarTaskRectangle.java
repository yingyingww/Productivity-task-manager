package finalProject;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CalendarTaskRectangle extends StackPane {
    public Label taskName;
    public int height;
    public int startPoint;
    public Color color;

    public CalendarTaskRectangle(Label taskName, int height, int startPoint, Color color) {
        this.taskName = taskName;
        this.height = height;
        this.startPoint = startPoint;
        this.color = color;
    }

    public StackPane setTaskRectangleAsStack() {
        StackPane stack = new StackPane();

        Rectangle taskRectangle = new Rectangle(100, height);
        taskRectangle.setFill(color);
        taskRectangle.setY(startPoint);

        taskName.setTextFill(Color.WHITE);

        stack.getChildren().addAll(taskRectangle, taskName);

        return stack;
    }
}

package finalProject;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CalendarTaskRectangle extends StackPane {
    public Label taskName;
    public float height;
    public float startPoint;
    //public Color color;

    public CalendarTaskRectangle(Label taskName, float height, float startPoint) {
        this.taskName = taskName;
        this.height = height;
        this.startPoint = startPoint;
        //this.color = color;
    }

    public StackPane setTaskRectangleAsStack() {
        StackPane stack = new StackPane();

        Rectangle taskRectangle = new Rectangle(100, height);
        taskRectangle.setFill(Color.WHITE);
        taskRectangle.setStroke(Color.BLACK);
        taskRectangle.setArcWidth(10);
        taskRectangle.setArcHeight(10);

        taskName.setTextFill(Color.BLACK);

        stack.getChildren().addAll(taskRectangle, taskName);
        stack.relocate(0,startPoint);

        return stack;
    }
}

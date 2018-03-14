package finalProject;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CalendarTaskRectangle extends StackPane {
    private Label taskName;
    private float height;
    private float startPoint;

    public CalendarTaskRectangle(Label taskName, float height, float startPoint) {
        this.taskName = taskName;
        this.height = height;
        this.startPoint = startPoint;
    }

    public StackPane setTaskRectangleAsStack() {
        StackPane stack = new StackPane();

        height = heightCheck(height);

        Rectangle taskRectangle = new Rectangle(200, height);
        taskRectangle = makeRectanglePretty(taskRectangle);

        stack.getChildren().addAll(taskRectangle, taskName);
        stack.relocate(0,startPoint);

        return stack;
    }

    private float heightCheck(float height) {
        if(height < 15) {
            height = 15;
        }
        return height;
    }

    private Rectangle makeRectanglePretty(Rectangle taskRectangle) {
        taskRectangle.setFill(Color.WHITE);
        taskRectangle.setStroke(Color.BLACK);
        taskRectangle.setArcWidth(10);
        taskRectangle.setArcHeight(10);

        taskName.setTextFill(Color.BLACK);

        return taskRectangle;
    }
}

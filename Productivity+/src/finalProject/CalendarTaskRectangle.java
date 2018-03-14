package finalProject;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * The java class creates rectangles that hold information for the calendar to be displayed on GUI pages
 */

public class CalendarTaskRectangle extends StackPane {
    private Label taskName;
    private float height;
    private float startPoint;

    public CalendarTaskRectangle(Label taskName, float height, float startPoint) {
        this.taskName = taskName;
        this.height = height;
        this.startPoint = startPoint;
    }

    /*
     * Takes the information associated with a CalendarTaskRectangle and turns it into a stack pane
     * that can be displayed by the user
     */
    public StackPane setTaskRectangleAsStack() {
        StackPane stack = new StackPane();

        // Make sure rectangle isn't too small to make sense on the calendar
        height = heightCheck(height);

        Rectangle taskRectangle = new Rectangle(200, height);
        taskRectangle = makeRectanglePretty(taskRectangle);

        stack.getChildren().addAll(taskRectangle, taskName);
        stack.relocate(0,startPoint);

        return stack;
    }

    // Make the minimum height be set to 15
    private float heightCheck(float height) {
        if(height < 15) {
            height = 15;
        }
        return height;
    }

    // Set the colors, borders, and corners of the rectangle
    private Rectangle makeRectanglePretty(Rectangle taskRectangle) {
        taskRectangle.setFill(Color.WHITE);
        taskRectangle.setStroke(Color.BLACK);
        taskRectangle.setArcWidth(10);
        taskRectangle.setArcHeight(10);

        taskName.setTextFill(Color.BLACK);

        return taskRectangle;
    }
}

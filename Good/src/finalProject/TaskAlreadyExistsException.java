package finalProject;

/*
 * Thrown when the user tries to input a task on main that already exists
 */

public class TaskAlreadyExistsException extends Exception {
    public TaskAlreadyExistsException(String message) {
        super(message);
    }
}

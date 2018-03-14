package finalProject;

/*
 * Thrown when no name was given when a user tried to input a task
 */

public class EmptyTaskNameException extends Exception {
    public EmptyTaskNameException(String message) {
        super(message);
    }
}
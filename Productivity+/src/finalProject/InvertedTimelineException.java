package finalProject;

/*
 * Thrown when the user inputs a start time after their end time
 */

public class InvertedTimelineException extends Exception {
    public InvertedTimelineException(String message) {
        super(message);
    }
}
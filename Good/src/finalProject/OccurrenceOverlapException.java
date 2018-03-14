package finalProject;

/*
 * Throws and exception when the user wants to input a task that overlaps with one that already exists
 */

public class OccurrenceOverlapException extends Exception {
    public OccurrenceOverlapException(String message) {
        super(message);
    }
}

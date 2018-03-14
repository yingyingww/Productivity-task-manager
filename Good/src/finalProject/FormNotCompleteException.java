package finalProject;

/*
 * Thrown when the user does not fill out all of the items in the form
 */

public class FormNotCompleteException extends Exception {
    public FormNotCompleteException(String message) {
        super(message);
    }
}

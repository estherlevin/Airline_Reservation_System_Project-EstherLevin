package advancedProgrammingProject;

/*Exception if date or time entered is invalid*/
public class InvalidTimeException extends RuntimeException {
	
	//no-arg constructor gives message to display to user
	public InvalidTimeException() {
		super("Invalid time and date entered.");
	}
	
	/**
	 * @param message to display to user 
	 * constructor allows client to choose message
	 */
	public InvalidTimeException(String message) {
		super(message);
	}
}

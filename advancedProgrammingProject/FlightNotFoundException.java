package advancedProgrammingProject;

/*Exception if date or time entered is invalid*/
public class FlightNotFoundException extends RuntimeException {
	
	//no-arg constructor gives message to display to user
	public FlightNotFoundException() {
		super("Flight does not exists.");
	}
	
	/**
	 * @param message to display to user 
	 * constructor allows client to choose message
	 */
	public FlightNotFoundException(String message) {
		super(message);
	}
}

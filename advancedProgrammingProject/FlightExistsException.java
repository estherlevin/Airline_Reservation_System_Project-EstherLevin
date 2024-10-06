package advancedProgrammingProject;
/* Exception to be thrown if flight object doesn't exist */
public class FlightExistsException extends RuntimeException {
	
	//no-arg constructor will display default message
	public FlightExistsException() {
		super("Error: flight already exist.");
		}
		
	/**
	 * @param message to display to user 
	 * constructor allows client to choose message
	 */
	public FlightExistsException(String message) {
			super(message);
	}
}

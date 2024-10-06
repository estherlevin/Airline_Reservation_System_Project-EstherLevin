package advancedProgrammingProject;
/* Exception will be thrown if reservation already exist */

public class ReservationExistsException extends RuntimeException {		
	//no-arg constructor will display default message
	public ReservationExistsException() {
		super("Reservation already exist.");
		}
			
	/**
	 * @param message to display to user 
	 * constructor allows client to choose message
	 */
	public ReservationExistsException(String message) {
			super(message);
	}
}

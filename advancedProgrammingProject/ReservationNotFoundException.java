package advancedProgrammingProject;

/* Exception will be thrown if reservation does not exist */
public class ReservationNotFoundException extends RuntimeException {		
	//no-arg constructor will display default message
	public ReservationNotFoundException() {
		super("Reservation was not found.");
		}
			
	/**
	 * @param message to display to user 
	 * constructor allows client to choose message
	 */
	public ReservationNotFoundException(String message) {
			super(message);
	}
}

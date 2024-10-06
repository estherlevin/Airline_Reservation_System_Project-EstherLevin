package advancedProgrammingProject;

/* Exception will be thrown if employee already exist */
public class EmployeeExistsException extends RuntimeException {		
	//no-arg constructor will display default message
	public EmployeeExistsException() {
		super("An employee with the provided ID already exist in the system.");
	}
			
	/**
	 * @param message to display to user 
	 * constructor allows client to choose message
	 */
	public EmployeeExistsException(String message) {
			super(message);
	}
}

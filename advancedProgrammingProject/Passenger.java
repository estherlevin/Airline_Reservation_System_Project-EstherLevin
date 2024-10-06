package advancedProgrammingProject;
import java.time.LocalDate;

/* Class holds field values of basic details for every traveling passenger */
public class Passenger extends Person {
	private int bookingReference;
	private String passportNumber;
	private String nationality;

	/**@param all fields values for both Passenger and Person classes
	 * birthdate is received as type String */
	public Passenger(String firstName, String lastName, Gender gender, Address address, 
			String birthdate, int bookingReference, String passportNumber,String nationality) {
		
		//makes a implicit call to constructor that receives all fields and birthdate as a LocalDate
		this(firstName,lastName,gender,address,LocalDate.parse(birthdate),
				bookingReference,passportNumber,nationality);
	}
	
	/**@param all fields values for both Passenger and Person classes 
	 * birthdate is received as type LocalDate */
	public Passenger(String firstName, String lastName, Gender gender, Address address, 
			LocalDate birthdate, int bookingReference, String passportNumber,String nationality) {
		//passing Person fields into superclass
		super(firstName,lastName,gender,address,birthdate);
		
		//validating that bookingReference is not less than 0
		if(bookingReference < 0)
			throw new IllegalArgumentException("Invalid data entered.");
		
		//setting fields
		this.bookingReference = bookingReference;
		this.passportNumber = passportNumber;
		this.nationality = nationality;
	}
	
	/**@param object to be copied through copy constructor */
	public Passenger(Passenger p) {
		
		//makes constructor call to validated constructor
		this(p.getFirstName(),p.getLastName(),p.getGender(),p.getAddress(),p.getBirthdate(),
				p.getBookingReference(),p.getPassportNumber(),p.getNationality());
	}
	
	/**@param values for fields to be set to - setters */
	public void setBookingReference(int bookingReference) {
		//validating that bookingReference is not less than 0
		if(bookingReference < 0)
			throw new IllegalArgumentException("Invalid data entered.");
				
		//setting bookingReference
		this.bookingReference = bookingReference;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	/**@return values of fields - getters */
	public int getBookingReference() {
		return bookingReference;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public String getNationality() {
		return nationality;
	}
	
	/**@return String of fields and their values */
	//overrides default toString() method
	@Override
	public String toString() {
		//appending parent toString() method
		StringBuilder str = new StringBuilder(super.toString());
		str.append(", Booking reference: " + bookingReference);
		str.append("\nPassport number: " + passportNumber + ", Nationality: " + nationality);
		
		//returns StringBuilder object as a String
		return str.toString();
	}
	
	/**@return boolean status of true or false if compared objects are the same or not */
	//overrides default equals() method 
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null)
			return false;
		if(getClass() != o.getClass())
			return false;
		
		//casting o to type Passenger so it can be compared to Passenger fields
		Passenger other = (Passenger)o;
		
		//comparing passportNumber Strings
		if(passportNumber == null) {
			if(other.passportNumber != null)
				return false;
		}
		else {
			if(!passportNumber.equals(other.passportNumber))
				return false;
		}
		
		//if didn't return false already, will return true since objects are equal
		return true;
	}
}
